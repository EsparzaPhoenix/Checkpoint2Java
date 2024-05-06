package br.com.fiap.biblioteca.dto.livro;

import jakarta.validation.constraints.Size;


public record AtualizarLivroDTO(
        @Size(max = 100)
        String titulo,
        @Size(max = 100)
        String sinopse) {
}
