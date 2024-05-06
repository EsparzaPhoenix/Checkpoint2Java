package br.com.fiap.biblioteca.dto.cliente;

import br.com.fiap.biblioteca.model.Cliente;

public record ListagemClienteDTO(Long codigo, String nome, String email) {

    public ListagemClienteDTO(Cliente cliente){
        this(cliente.getCodigo(), cliente.getNome(), cliente.getEmail());
    }

}
