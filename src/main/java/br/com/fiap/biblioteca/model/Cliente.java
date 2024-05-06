package br.com.fiap.biblioteca.model;

import br.com.fiap.biblioteca.model.Emprestimo;
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
@Table(name = "jv_cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {

    @Id
    @GeneratedValue
    @Column(name = "cd_cliente")
    private Long codigo;

    @Column(name = "nm_cliente", length = 100, nullable = false)
    private String nome;

    @Column(name = "ds_email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "ds_senha", length = 10, nullable = false, unique = true)
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;

//    public Cliente(CadastrarClienteDTO dto) {
//        this.nome = dto.nome();
//        this.email = dto.email();
//        this.senha = dto.senha();
//    }

}