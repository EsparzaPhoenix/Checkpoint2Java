package br.com.fiap.biblioteca.dto.autor;

import br.com.fiap.biblioteca.model.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ListagemAutorDTO(Long codigo, String nome, String email, String biografia,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                               LocalDateTime dataCadastro) {

    public ListagemAutorDTO(Autor autor) {
        this(autor.getCodigo(), autor.getNome(), autor.getEmail(), autor.getBiografia(),
                autor.getDataCadastro());
    }
}
