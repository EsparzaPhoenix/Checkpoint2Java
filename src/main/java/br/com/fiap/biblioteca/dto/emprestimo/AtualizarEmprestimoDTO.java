package br.com.fiap.biblioteca.dto.emprestimo;

import br.com.fiap.biblioteca.model.enums.StatusEmprestimo;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarEmprestimoDTO(
        @Future
        LocalDate dataDevolucao) {
}
