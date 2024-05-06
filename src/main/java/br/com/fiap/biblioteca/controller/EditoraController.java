package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.model.Editora;
import br.com.fiap.biblioteca.dto.editora.*;
import br.com.fiap.biblioteca.repository.EditoraRepository;
import br.com.fiap.biblioteca.repository.LivroRepository;
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
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    // cadastrar uma editora
    @PostMapping
    @Transactional
    public ResponseEntity<ListagemEditoraDTO> cadastrar(@RequestBody CadastrarEditoraDTO dto, UriComponentsBuilder builder){
        // Cria uma nova editora com base nos dados recebidos
        var editora = new Editora(dto);
        // Salva a editora no banco
        repository.save(editora);
        // Cria a URI para a editora cadastrada
        var uri = builder.path("/{id}").buildAndExpand(editora.getCodigo()).toUri();
        // Retorna uma resposta da editora cadastrada
        return ResponseEntity.created(uri).body(new ListagemEditoraDTO(editora));
    }

    // listar as editoras
    @GetMapping
    public ResponseEntity<Page<ListagemEditoraDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable){
        // Busca as editoras paginadas e mapeia
        var page = repository.findAll(pageable).map(ListagemEditoraDTO::new);
        // Retorna uma resposta com a página de editoras
        return ResponseEntity.ok(page);
    }

    // buscar uma editora por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesEditoraDTO> buscarPorId(@PathVariable Long id){
        // Busca a editora pelo ID
        var editora = repository.getReferenceById(id);
        // Retorna uma resposta da editora
        return ResponseEntity.ok().body(new DetalhesEditoraDTO(editora));
    }

    // atualizar uma editora
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesEditoraDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarEditoraDTO dto){
        // Busca a editora pelo ID
        var editora = repository.getReferenceById(id);
        // Atualiza os dados da editora com base nos dados recebidos no DTO
        editora.atualizarEditora(dto);
        // Retorna uma resposta com o DTO detalhado da editora atualizada
        return ResponseEntity.ok().body(new DetalhesEditoraDTO(editora));
    }

    // remover um livro associado a uma editora
    @DeleteMapping("/{idLivro}/livros/")
    @Transactional
    public ResponseEntity<Void> removerLivro(@PathVariable Long idLivro){
        // Busca o livro pelo ID
        var livro = livroRepository.getReferenceById(idLivro);
        // Remove a associação do livro com a editora
        livro.setEditora(null);
        // Salva as alterações no banco
        livroRepository.save(livro);
        // Retorna uma resposta de No Content
        return ResponseEntity.noContent().build();
    }

}
