package br.com.fiap.biblioteca.dto.editora;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;

public record AtualizarEditoraDTO(String nome,
                                  String telefone,
                                  String email) {
}

