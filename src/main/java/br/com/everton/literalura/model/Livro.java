package br.com.everton.literalura.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

   private String idioma;

   @Column(name = "qtd_downloads")
   private BigDecimal numeroDownloads;

   @ToString.Exclude
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
       name = "livro_autor",
       joinColumns = @JoinColumn(name = "livro_id"),
       inverseJoinColumns = @JoinColumn(name = "autor_id")
   )
   private Set<Autor> autores = new HashSet<>();

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof Livro livro)) return false;
      return id != null && id.equals(livro.id);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }
}
