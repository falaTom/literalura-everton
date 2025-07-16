package br.com.rafaellima.literalura.repository;

import br.com.rafaellima.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
