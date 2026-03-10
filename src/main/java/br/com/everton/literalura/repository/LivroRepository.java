package br.com.everton.literalura.repository;

import br.com.everton.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
   List<Livro> findByIdiomaIgnoreCase(String idioma);
}
