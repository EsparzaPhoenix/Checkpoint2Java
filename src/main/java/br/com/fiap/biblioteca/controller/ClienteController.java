package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dto.cliente.CadastrarClienteDTO;
import br.com.fiap.biblioteca.dto.cliente.ListagemClienteDTO;
import br.com.fiap.biblioteca.model.Cliente;
import br.com.fiap.biblioteca.dto.cliente.AtualizarClienteDTO;
import br.com.fiap.biblioteca.dto.cliente.DetalhesClienteDTO;
import br.com.fiap.biblioteca.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
// Define o caminho dos endpoints
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    // cadastrar um cliente
    @PostMapping
    @Transactional
    public ResponseEntity<ListagemClienteDTO> cadastrar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        // Cria um novo cliente com base nos dados recebidos no DTO
        var cliente = new Cliente(dto);
        // Salva o cliente no banco
        repository.save(cliente);
        // Cria a URI para o cliente cadastrado
        var uri = builder.path("/{id}").buildAndExpand(cliente.getCodigo()).toUri();
        // Retorna uma resposta do cliente cadastrado
        return ResponseEntity.created(uri).body(new ListagemClienteDTO(cliente));
    }

    // listar os clientes
    @GetMapping
    public ResponseEntity<Page<ListagemClienteDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable){
        // Busca os clientes paginados e mapeia
        var page = repository.findAll(pageable).map(ListagemClienteDTO::new);
        // Retorna uma resposta com a página de clientes
        return ResponseEntity.ok(page);
    }

    // Endpoint para buscar um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesClienteDTO> buscarPorId(@PathVariable Long id){
        // Busca o cliente pelo ID
        var cliente = repository.getReferenceById(id);
        // Retorna uma resposta do cliente
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    // atualizar um cliente
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarClienteDTO dto){
        // Busca o cliente pelo ID
        var cliente = repository.getReferenceById(id);
        // Atualiza os dados do cliente
        cliente.atualizarCliente(dto);
        // Retorna uma resposta do cliente atualizado
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    // excluir um cliente
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        // Busca o cliente pelo ID
        var cliente = repository.getReferenceById(id);
        // Exclui o cliente do banco
        repository.deleteById(cliente.getCodigo());
        // Retorna uma resposta No Content
        return ResponseEntity.noContent().build();
    }

}
