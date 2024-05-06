package br.com.fiap.biblioteca.model;

import br.com.fiap.biblioteca.model.enums.Genero;
import br.com.fiap.biblioteca.dto.livro.CadastrarLivroDTO;
import br.com.fiap.biblioteca.dto.livro.AtualizarLivroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "jv_detalhes_livro")
@EntityListeners(AuditingEntityListener.class)
public class DetalhesLivro {

    @Id
    @GeneratedValue
    @Column(name = "cd_detalhes_livro")
    private Long codigo;

    @Column(name = "nr_isbn", length = 13, nullable = false, unique = true)
    private String isbn;

    @Column(name = "ds_sinopse", length = 100, nullable = false)
    private String sinopse;

    @Column(name = "dt_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "ds_genero", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @OneToOne(mappedBy = "detalhesLivro", cascade = CascadeType.ALL)
    private Livro livro;

    public DetalhesLivro(CadastrarLivroDTO dto){
        this.isbn = dto.numeroIsbn();
        this.sinopse = dto.sinopse();
        this.dataPublicacao = dto.dataPublicacao();
        this.genero = dto.genero();
    }

    public void atualizar(AtualizarLivroDTO dto){
        if(dto.sinopse() != null){
            this.sinopse = dto.sinopse();
        }
    }

}