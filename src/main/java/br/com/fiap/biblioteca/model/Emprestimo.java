package br.com.fiap.biblioteca.model;

import br.com.fiap.biblioteca.dto.emprestimo.AtualizarEmprestimoDTO;
import br.com.fiap.biblioteca.dto.emprestimo.CadastrarEmprestimoDTO;
import br.com.fiap.biblioteca.model.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "jv_emprestimo")
@EntityListeners(AuditingEntityListener.class)
public class Emprestimo {

    @Id
    @GeneratedValue
    @Column(name = "cd_emprestimo")
    private Long codigo;

    @Column(name = "dt_emprestimo", nullable = false)
    @CreatedDate
    private LocalDate dataEmprestimo;

    @Column(name = "dt_devolucao", nullable = false)
    private LocalDate dataDevolucao;

    @Column(name = "st_emprestimo", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToMany
    @JoinTable(name = "jv_tb_livro_emprestimo",
            joinColumns = @JoinColumn(name = "cd_emprestimo"),
            inverseJoinColumns = @JoinColumn(name = "cd_livro"))
    private List<Livro> livros;

    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;

    public Emprestimo(CadastrarEmprestimoDTO dto){
        this.dataDevolucao = dto.dataDevolucao();
        this.livros = new ArrayList<>();
    }

    public void atualizarEmprestimo(AtualizarEmprestimoDTO dto){
        if(dto.dataDevolucao() != null){
            this.dataDevolucao = dto.dataDevolucao();
        }
    }

}