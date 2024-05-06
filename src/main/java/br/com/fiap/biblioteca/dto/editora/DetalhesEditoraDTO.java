package br.com.fiap.biblioteca.dto.editora;

import br.com.fiap.biblioteca.model.Editora;
import br.com.fiap.biblioteca.model.Livro;
import br.com.fiap.biblioteca.dto.livro.ListagemLivroDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhesEditoraDTO(Long codigo, String nome, String telefone, String email, ArrayList<ListagemLivroDTO> livros) {

    public DetalhesEditoraDTO(Editora editora){
        this(editora.getCodigo(), editora.getNome(), editora.getTelefone(), editora.getEmail(),
                new ArrayList<>(editora.getLivro().stream().map(livro -> new ListagemLivroDTO(livro.getCodigo(),
                        livro.getTitulo(), livro.getDetalhesLivro().getSinopse(), livro.getDetalhesLivro().getSinopse(),
                        livro.getDetalhesLivro().getGenero())).collect(Collectors.toList())));
    }

}
