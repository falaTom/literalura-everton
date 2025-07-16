package br.com.rafaellima.literalura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Livro {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String titulo;

   private String autor;

   private String idioma;

   private Integer numeroDownloads;

   @ToString.Exclude
   @ManyToMany
   @JoinTable(
       name = "livro_autor",
       joinColumns = @JoinColumn(name = "livro_id"),
       inverseJoinColumns = @JoinColumn(name = "autor_id")
   )
   private Set<Autor> autores = new HashSet<>();

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof Livro)) return false;
      Livro livro = (Livro) obj;
      return id != null && id.equals(livro.id);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }
}
