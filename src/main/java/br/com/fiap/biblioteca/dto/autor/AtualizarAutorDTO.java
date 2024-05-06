package br.com.fiap.biblioteca.dto.autor;

import jakarta.validation.constraints.*;

public record AtualizarAutorDTO(
        @Size(max = 100)
        String nome,
        @Size(max = 100)
        String biografia,
        @Email
        @Size(max = 100)
        String email) {
}
