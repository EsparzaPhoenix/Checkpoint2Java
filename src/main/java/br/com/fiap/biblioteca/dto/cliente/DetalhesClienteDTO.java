package br.com.fiap.biblioteca.dto.cliente;

import br.com.fiap.biblioteca.dto.emprestimo.DetalhesEmprestimoDTO;
import br.com.fiap.biblioteca.dto.emprestimo.ListagemEmprestimoDTO;
import br.com.fiap.biblioteca.model.Cliente;
import java.util.ArrayList;
import java.util.stream.Collectors;

public record DetalhesClienteDTO(Long codigo, String nome, String email, ArrayList<ListagemEmprestimoDTO> emprestimos) {

    public DetalhesClienteDTO(Cliente cliente){
        this(cliente.getCodigo(), cliente.getNome(), cliente.getEmail(),
                new ArrayList<>(cliente.getEmprestimos().stream().map(emprestimo -> new ListagemEmprestimoDTO(emprestimo.getCodigo(),
                        emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao(), emprestimo.getStatus())).collect(Collectors.toList())));
    }
}
