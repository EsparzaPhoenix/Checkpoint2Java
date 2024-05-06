package br.com.fiap.biblioteca.dto.emprestimo;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarEmprestimoDTO(
        @NotNull
        @Future
        LocalDate dataDevolucao) {
}