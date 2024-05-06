package br.com.fiap.biblioteca.dto.editora;

import br.com.fiap.biblioteca.model.Livro;

public record DetalhesEditoraLivroDTO(Long codigo, String titulo, String sinopse, String isbn, ListagemEditoraDTO editora) {

    public DetalhesEditoraLivroDTO(Livro livro){
        this(livro.getCodigo(), livro.getTitulo(), livro.getDetalhesLivro().getSinopse(), livro.getDetalhesLivro().getIsbn(), new ListagemEditoraDTO(livro.getEditora()));
    }
}
