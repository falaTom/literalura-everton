package br.com.rafaellima.literalura.repository;

import br.com.rafaellima.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
