package br.com.fiap.biblioteca.repository;


import br.com.fiap.biblioteca.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
