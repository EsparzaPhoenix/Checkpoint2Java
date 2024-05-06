package br.com.fiap.biblioteca.dto.livro;

import br.com.fiap.biblioteca.model.enums.Genero;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastrarLivroDTO(
        @NotEmpty
        String titulo,
        @NotEmpty
        @Size(max = 13)
        String numeroIsbn,
        @NotEmpty
        String sinopse,
        @NotNull
        LocalDate dataPublicacao,
        @NotNull
        Genero genero) {
}