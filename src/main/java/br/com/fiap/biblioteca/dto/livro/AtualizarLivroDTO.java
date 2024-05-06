package br.com.fiap.biblioteca.dto.livro;

import br.com.fiap.biblioteca.model.enums.Genero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizarLivroDTO(
        @Size(max = 100)
        String titulo,
        @Size(max = 100)
        String sinopse) {
}
