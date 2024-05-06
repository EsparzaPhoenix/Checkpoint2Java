package br.com.fiap.biblioteca.dto.emprestimo;

import br.com.fiap.biblioteca.dto.cliente.DetalhesClienteDTO;
import br.com.fiap.biblioteca.model.Emprestimo;
import br.com.fiap.biblioteca.model.enums.StatusEmprestimo;
import br.com.fiap.biblioteca.dto.livro.ListagemLivroDTO;
import br.com.fiap.biblioteca.dto.cliente.DetalhesClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public record DetalhesEmprestimoDTO(Long codigo,
                                    @JsonFormat(pattern = "dd/MM/yyyy")
                                    LocalDate dataEmprestimo,
                                    @JsonFormat(pattern = "dd/MM/yyyy")
                                    LocalDate dataDevolucao, StatusEmprestimo status, DetalhesClienteDTO cliente, ArrayList<ListagemLivroDTO> livro) {

    public DetalhesEmprestimoDTO(Emprestimo emprestimo) {
        this(emprestimo.getCodigo(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao(), emprestimo.getStatus(), new DetalhesClienteDTO(emprestimo.getCliente()),
                new ArrayList<>(emprestimo.getLivros().stream().map(livro -> new ListagemLivroDTO(livro.getCodigo(), livro.getTitulo(), livro.getDetalhesLivro().getSinopse(), livro.getDetalhesLivro().getIsbn(), livro.getDetalhesLivro().getGenero())).collect(Collectors.toList())));
    }
}