package br.com.rafaellima.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
   private String title;
   private List<Author> authors;
   private List<String> languages;
   private BigDecimal download_count;

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   public static class Author {
      private String name;
      private Integer birth_year;
      private Integer death_year;
   }
}
