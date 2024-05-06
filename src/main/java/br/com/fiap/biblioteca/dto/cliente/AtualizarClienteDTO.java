package br.com.fiap.biblioteca.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AtualizarClienteDTO(
        @Size(max = 100)
        String nome,
        @Size(max = 100)
        @Email
        String email,
        @Size(max = 10)
        String senha) {
}
