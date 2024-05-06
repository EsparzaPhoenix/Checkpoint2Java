package br.com.fiap.biblioteca.dto.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CadastrarAutorDTO(
        @NotBlank
        String nome,
        @NotBlank
        String biografia,
        @NotBlank
        @Email
        String email) {
}
