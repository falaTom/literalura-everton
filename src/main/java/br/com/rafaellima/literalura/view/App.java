package br.com.rafaellima.literalura.view;

import br.com.rafaellima.literalura.repository.AutorRepository;
import br.com.rafaellima.literalura.repository.LivroRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class App {
   private LivroRepository livroRepository;
   private AutorRepository autorRepository;

   public static void main(String[] args) {

   }

   public void menu(){}
}
