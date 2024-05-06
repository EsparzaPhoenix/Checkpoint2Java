package br.com.fiap.biblioteca.dto.autor;

import br.com.fiap.biblioteca.model.Autor;
import br.com.fiap.biblioteca.dto.livro.ListagemLivroDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public record DetalhesAutorDTO(Long codigo, String nome, String email, String biografia,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                               LocalDateTime dataCadastro, ArrayList<ListagemLivroDTO> livros) {

    public DetalhesAutorDTO(Autor autor){
        this(autor.getCodigo(), autor.getNome(), autor.getEmail(), autor.getBiografia(), autor.getDataCadastro(),
                new ArrayList<>(autor.getLivros().stream().map(livro -> new ListagemLivroDTO(livro.getCodigo(), livro.getTitulo(), livro.getDetalhesLivro().getSinopse(),
                        livro.getDetalhesLivro().getIsbn(), livro.getDetalhesLivro().getGenero())).collect(Collectors.toList())));
    }

}
