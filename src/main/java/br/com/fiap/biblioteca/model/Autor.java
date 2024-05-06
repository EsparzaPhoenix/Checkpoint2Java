package br.com.fiap.biblioteca.model;

import br.com.fiap.biblioteca.model.Livro;
import br.com.fiap.biblioteca.dto.autor.CadastrarAutorDTO;
import br.com.fiap.biblioteca.dto.autor.AtualizarAutorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "jv_autor")
@EntityListeners(AuditingEntityListener.class)
public class Autor {

    @Id
    @GeneratedValue
    @Column(name = "cd_autor")
    private Long codigo;

    @Column(name = "nm_autor", length = 100, nullable = false)
    private String nome;

    @Column(name = "dt_cadastro", nullable = false)
    @CreatedDate
    private LocalDateTime dataCadastro;

    @Column(name = "ds_biografia", length = 100, nullable = false)
    private String biografia;

    @Column(name = "ds_email", length = 100, nullable = false)
    private String email;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    public Autor(CadastrarAutorDTO dto){
        this.nome = dto.nome();
        this.biografia = dto.biografia();
        this.email = dto.email();
    }

    public void atualizar(AtualizarAutorDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.biografia() != null){
            this.biografia = dto.biografia();
        }
        if(dto.email() != null){
            this.email = dto.email();
        }
    }

}