package br.com.fiap.biblioteca.dto.livro;

import br.com.fiap.biblioteca.dto.editora.DetalhesEditoraDTO;
import br.com.fiap.biblioteca.model.DetalhesLivro;
import br.com.fiap.biblioteca.model.enums.Genero;
import br.com.fiap.biblioteca.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record ListagemLivroDTO(Long codigo, String titulo, String sinopse, String isbn, Genero genero) {

    public ListagemLivroDTO(Livro livro){
        this(livro.getCodigo(), livro.getTitulo(), livro.getDetalhesLivro().getSinopse(), livro.getDetalhesLivro().getIsbn(),livro.getDetalhesLivro().getGenero());
    }
}
