package br.com.fiap.biblioteca.model;

import br.com.fiap.biblioteca.dto.editora.AtualizarEditoraDTO;
import br.com.fiap.biblioteca.dto.editora.CadastrarEditoraDTO;
import br.com.fiap.biblioteca.model.Livro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "jv_editora")
@EntityListeners(AuditingEntityListener.class)
public class Editora {

    @Id
    @GeneratedValue
    @Column(name = "cd_editora")
    private Long codigo;

    @Column(name = "nm_editora", length = 100, nullable = false)
    private String nome;

    @Column(name = "nr_telefone", length = 11, nullable = false)
    private String telefone;

    @Column(name = "ds_email", length = 100, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL)
    private List<Livro> livro;

    public Editora(CadastrarEditoraDTO dto){
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }

    public void atualizarEditora(AtualizarEditoraDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.telefone() != null){
            this.telefone = dto.telefone();
        }
        if(dto.email() != null){
            this.email = dto.email();
        }
    }
}