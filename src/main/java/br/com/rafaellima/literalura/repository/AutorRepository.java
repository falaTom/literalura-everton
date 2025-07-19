package br.com.rafaellima.literalura.repository;

import br.com.rafaellima.literalura.dto.AutorLivroDTO;
import br.com.rafaellima.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
   Autor findByNome(String name);

   @Query("""
  SELECT new br.com.rafaellima.literalura.dto.AutorLivroDTO(
    a.nome, a.anoNascimento, a.anoFalecimento, l.titulo
  )
  FROM Autor a
  JOIN a.livros l
  WHERE :ano BETWEEN a.anoNascimento AND a.anoFalecimento
""")
   List<AutorLivroDTO> buscarAutoresVivosPorAno(@Param("ano") Integer ano);

}
