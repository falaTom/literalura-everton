package br.com.everton.literalura.view;

import br.com.everton.literalura.dto.AutorLivroDTO;
import br.com.everton.literalura.dto.GutendexPage;

import br.com.everton.literalura.model.Autor;
import br.com.everton.literalura.model.Livro;
import br.com.everton.literalura.repository.AutorRepository;
import br.com.everton.literalura.repository.LivroRepository;
import br.com.everton.literalura.service.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class App {
   private LivroRepository livroRepository;
   private AutorRepository autorRepository;
   Scanner sc = new Scanner(System.in);
   ConsumoApi consumoApi = new ConsumoApi();

   final String ENDERECO_API = "https://gutendex.com/books/?search=";

   public App(LivroRepository livroRepository, AutorRepository autorRepository) {
      this.livroRepository = livroRepository;
      this.autorRepository = autorRepository;
   }

   public void menu() throws JsonProcessingException {

      var opcao = -1;

      // Monte o menu de opções
      while (opcao != 0) {

         System.out.println("**********************************");
         System.out.println(" --- Bem-vindo(a) ao Literalura ---");
         System.out.println("***********************************");
         System.out.println("1 - Buscar livros por título");
         System.out.println("2 - Listar livros já cadastrados");
         System.out.println("3 - Listar autores já cadastrados");
         System.out.println("4 - Listar autores vivos em um ano específico");
         System.out.println("5 - Listar livros por um idioma específico");
         System.out.println("0 - Sair");
         System.out.println("***********************************");
         System.out.print("Escolha uma opção: ");
         try {
            opcao = Integer.parseInt(sc.nextLine());
         } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Por favor, digite um número.");
            continue;
         }

         switch (opcao) {
            case 1:
               buscarLivrosPorTitulo();
               break;
            case 2:
               listarLivrosCadastrados();
               break;
            case 3:
               listarAutoresCadastrados();
               break;
            case 4:
               listarAutoresVivosEmAnoEspecifico();
               break;
            case 5:
               listarLivrosPorIdiomaEspecifico();
               break;
            case 0:
               System.out.println("Saindo do programa. Até logo!");
               break;
            default:
               System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
               break;
         }

      }
      sc.close();
   }

   private void buscarLivrosPorTitulo() throws JsonProcessingException {
      System.out.print("Digite o título do livro que deseja buscar: ");
      String titulo = sc.nextLine();

      if (titulo.isEmpty()) {
         System.out.println("Título não pode ser vazio.");
         return;
      }

      String url = ENDERECO_API + titulo.replace(" ", "+");
      String json = consumoApi.obterDados(url);
      if (json.isEmpty()) {
         System.out.println("Nenhum livro encontrado com o título: " + titulo);
         return;
      }
      ObjectMapper mapper = new ObjectMapper();
      GutendexPage gutendexPage = mapper.readValue(json, GutendexPage.class);

      if (!gutendexPage.getResults().isEmpty()) {
         var livroGutendex = gutendexPage.getResults().getFirst();
         System.out.println("Livro encontrado: ");
         System.out.println("Título: " + livroGutendex.getTitle());
         livroGutendex.getAuthors().forEach(autor ->
               System.out.println(
                     "Autor: " + autor.getName() + " (Data de nascimento: " + autor.getBirth_year() + ")" +
                     (autor.getDeath_year() != null ? ", Data de falecimento: " + autor.getDeath_year() : "")
               )
         );
         System.out.println("Idioma: " + livroGutendex.getLanguages().stream().findFirst().orElse("Desconhecido"));
         System.out.println("Número de downloads: " + livroGutendex.getDownload_count());

         Livro livro = new Livro();
         livro.setTitulo(livroGutendex.getTitle());
         livro.setIdioma(livroGutendex.getLanguages().getFirst());
         livro.setNumeroDownloads(livroGutendex.getDownload_count());

         Set<Autor> autores = new HashSet<>();

         livroGutendex.getAuthors().forEach(autor -> {
            var autorExistente = autorRepository.findByNome(autor.getName());
            Autor autorLivro;
            if (autorExistente != null) {
               autorLivro = autorExistente;
            } else {
               autorLivro = new Autor();
               autorLivro.setNome(autor.getName());
               autorLivro.setAnoNascimento(autor.getBirth_year());
               autorLivro.setAnoFalecimento(autor.getDeath_year());
               autorLivro = autorRepository.save(autorLivro);
            }
            autores.add(autorLivro);
         });
         livro.setAutores(autores);
         livroRepository.save(livro);
      }

   }

   private void listarLivrosPorIdiomaEspecifico() {
      System.out.print("Digite o idioma que deseja buscar (pt, en, dn, etc...): ");
      String idioma = sc.nextLine();

      if (idioma.isEmpty()) {
         System.out.println("Idioma não pode ser vazio.");
         return;
      }

      var livros = livroRepository.findByIdiomaIgnoreCase(idioma);
      if (livros.isEmpty()) {
         System.out.println("Nenhum livro encontrado no idioma: " + idioma);
         return;
      }

      System.out.println("Livros encontrados no idioma " + idioma + ":");
      for (Livro livro : livros) {
         System.out.println("Título: " + livro.getTitulo());
         livro.getAutores().forEach(autor ->
               System.out.println("Autor: " + autor.getNome()
               )
         );
         System.out.println("Número de downloads: " + livro.getNumeroDownloads());
         System.out.println("Idioma: " + livro.getIdioma());
      }

   }

   private void listarAutoresVivosEmAnoEspecifico() {

      System.out.println("Digite o ano que deseja buscar: ");
      int ano = sc.nextInt();
      sc.nextLine(); // Limpar o buffer do scanner
      if (ano <= 0) {
         System.out.println("Ano inválido. Por favor, digite um ano válido.");
         return;
      }
      List<AutorLivroDTO> autoresVivos = autorRepository.buscarAutoresVivosPorAno(ano);
      if (autoresVivos.isEmpty()) {
         System.out.println("Nenhum autor vivo encontrado no ano: " + ano);
         return;
      }
      System.out.println("Autores vivos no ano " + ano + ":");
      for (AutorLivroDTO autor : autoresVivos) {
         System.out.println("Nome: " + autor.nome());
         System.out.println("Ano de nascimento: " + autor.anoNascimento());
         if (autor.anoFalecimento() != null) {
            System.out.println("Ano de falecimento: " + autor.anoFalecimento());
         }
         Autor autorExistente = autorRepository.findByNome(autor.nome());
         if (autorExistente == null) {
            System.out.println("Autor não encontrado no banco de dados.");
            continue;
         }
         String titulos = autorExistente.getLivros().stream()
               .map(Livro::getTitulo)
               .collect(Collectors.joining(", "));
         System.out.println("Livros: " + "[" + titulos + "]");
         System.out.println("-----------------------------");
      }


   }

   private void listarAutoresCadastrados() {

      System.out.println("Listando autores cadastrados:");
      List<Autor> autores = autorRepository.findAll();
      if (autores.isEmpty()) {
         System.out.println("Nenhum autor cadastrado.");
         return;
      }
      for (Autor autor : autores) {
         System.out.println("Nome: " + autor.getNome());
         System.out.println("Ano de nascimento: " + autor.getAnoNascimento());
         if (autor.getAnoFalecimento() != null) {
            System.out.println("Ano de falecimento: " + autor.getAnoFalecimento());
         }
         String titulos = autor.getLivros().stream()
               .map(Livro::getTitulo)
               .collect(Collectors.joining(", "));
         System.out.println("Livros: " + "[" + titulos + "]");
         System.out.println("-----------------------------");
      }
   }

   private void listarLivrosCadastrados() {
      System.out.println("Listando livros cadastrados:");
      List<Livro> livros = livroRepository.findAll();
      if (livros.isEmpty()) {
         System.out.println("Nenhum livro cadastrado.");
         return;
      }
      for (Livro livro : livros) {
         System.out.println("Título: " + livro.getTitulo());
         System.out.println("Idioma: " + livro.getIdioma());
         System.out.println("Número de downloads: " + livro.getNumeroDownloads());
         livro.getAutores().forEach(autor ->
               System.out.println("Autor: " + autor.getNome())
         );
         System.out.println("-----------------------------");
      }

   }

}
