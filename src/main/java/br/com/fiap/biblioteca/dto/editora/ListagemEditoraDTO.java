package br.com.fiap.biblioteca.dto.editora;

import br.com.fiap.biblioteca.model.Editora;
import br.com.fiap.biblioteca.model.Livro;

import java.util.List;

public record ListagemEditoraDTO(Long codigo, String nome, String telefone, String email) {

    public ListagemEditoraDTO(Editora editora){
        this(editora.getCodigo(), editora.getNome(), editora.getTelefone(), editora.getEmail());
    }
}
