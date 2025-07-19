package br.com.rafaellima.literalura.dto;

public record AutorLivroDTO(
      String nome,
      Integer anoNascimento,
      Integer anoFalecimento,
      String tituloLivro
) {
}
