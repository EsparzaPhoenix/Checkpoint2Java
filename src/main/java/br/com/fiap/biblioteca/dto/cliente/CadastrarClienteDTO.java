package br.com.fiap.biblioteca.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CadastrarClienteDTO(
        @NotEmpty
        @Size(max = 100)
        String nome,
        @Size(max = 100)
        @NotEmpty
        @Email
        String email,
        @Size(max = 10)
        @NotEmpty
        String senha) {
}
