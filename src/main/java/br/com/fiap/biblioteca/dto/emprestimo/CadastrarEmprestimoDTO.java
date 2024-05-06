package br.com.fiap.biblioteca.dto.emprestimo;


import br.com.fiap.biblioteca.model.enums.StatusEmprestimo;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CadastrarEmprestimoDTO(
        @NotNull
        @Future
        LocalDate dataDevolucao) {
}