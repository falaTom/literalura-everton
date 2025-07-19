package br.com.rafaellima.literalura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Autor {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;

   @Column(name = "ano_nascimento")
   private Integer anoNascimento;

   @Column(name = "ano_falecimento")
   private Integer anoFalecimento;

   @ToString.Exclude
   @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
   private Set<Livro> livros = new HashSet<>();

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof Autor)) return false;
      Autor autor = (Autor) obj;
      return id != null && id.equals(autor.id);
   }

   @Override
   public int hashCode() {
      return id != null ? id.hashCode() : 0;
   }
}
