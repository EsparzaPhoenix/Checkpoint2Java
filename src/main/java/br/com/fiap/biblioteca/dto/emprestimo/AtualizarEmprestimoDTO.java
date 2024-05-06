package br.com.fiap.biblioteca.dto.emprestimo;

import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record AtualizarEmprestimoDTO(
        @Future
        LocalDate dataDevolucao) {
}
