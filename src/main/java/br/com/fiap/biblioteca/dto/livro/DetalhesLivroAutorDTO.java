package br.com.fiap.biblioteca.dto.livro;

import br.com.fiap.biblioteca.dto.autor.ListagemAutorDTO;
import br.com.fiap.biblioteca.model.enums.Genero;
import br.com.fiap.biblioteca.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetalhesLivroAutorDTO(Long codigo, String titulo, String sinopse, String isbn,
                                    @JsonFormat(pattern = "dd/MM/yyyy")
                                    LocalDate dataPublicacao,
                                    Genero genero,
                                    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                    LocalDateTime dataCadastro, ListagemAutorDTO autor) {

    public DetalhesLivroAutorDTO(Livro livro){
        this(livro.getCodigo(), livro.getTitulo(), livro.getDetalhesLivro().getSinopse(), livro.getDetalhesLivro().getIsbn(),
                livro.getDetalhesLivro().getDataPublicacao(), livro.getDetalhesLivro().getGenero(), livro.getDataCadastro(),
                new ListagemAutorDTO(livro.getAutor()));
    }
}
