package br.com.fiap.biblioteca.repository;


import br.com.fiap.biblioteca.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
