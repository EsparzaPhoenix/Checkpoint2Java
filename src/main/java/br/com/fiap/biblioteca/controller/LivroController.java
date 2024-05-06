package br.com.fiap.biblioteca.controller;

import br.com.fiap.biblioteca.dto.editora.DetalhesEditoraLivroDTO;
import br.com.fiap.biblioteca.dto.livro.*;
import br.com.fiap.biblioteca.model.Livro;
import br.com.fiap.biblioteca.repository.AutorRepository;
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
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemLivroDTO> cadastrar(@RequestBody CadastrarLivroDTO dto, UriComponentsBuilder builder){
        // Cria um novo livro
        var livro = new Livro(dto);
        // Salva o livro no banco de dados
        livroRepository.save(livro);
        // URI para o livro cadastrado
        var uri = builder.path("/{id}").buildAndExpand(livro.getCodigo()).toUri();
        // Retorna a resposta do livro cadastrado
        return ResponseEntity.created(uri).body(new ListagemLivroDTO(livro));
    }

    // listar os livros
    @GetMapping
    public ResponseEntity<Page<ListagemLivroDTO>> listar(@PageableDefault(sort = {"titulo"}) Pageable pageable){
        // Busca os livros paginados
        var page = livroRepository.findAll(pageable).map(ListagemLivroDTO::new);
        // Retorna a resposta com a lista de livros paginada
        return ResponseEntity.ok(page);
    }

    // Endpoint para buscar um livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesLivroDTO> buscarPorId(@PathVariable Long id){
        // Busca o livro pelo ID
        var livro = livroRepository.getReferenceById(id);
        // Retorna a resposta detalhadada do livro
        return ResponseEntity.ok(new DetalhesLivroDTO(livro));
    }

    // atualizar um livro
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ListagemLivroDTO> atualizar(@RequestBody @Valid AtualizarLivroDTO dto, @PathVariable Long id){
        // Busca o livro pelo ID
        var livro = livroRepository.getReferenceById(id);
        // Atualiza as informações do livro com base no DTO recebido
        livro.atualizar(dto);
        // Retorna a resposta com o DTO atualizado do livro
        return ResponseEntity.ok().body(new ListagemLivroDTO(livro));
    }

    // incluir uma editora em um livro
    @PutMapping("{idLivro}/editoras/{idEditora}")
    @Transactional
    public ResponseEntity<DetalhesEditoraLivroDTO> incluirEditora(@PathVariable("idLivro") Long idLivro, @PathVariable("idEditora") Long idEditora){
        // Busca o livro pelo ID
        var livro = livroRepository.getReferenceById(idLivro);
        // Busca a editora pelo ID
        var editora = editoraRepository.getReferenceById(idEditora);
        // Define a editora para o livro
        livro.setEditora(editora);
        // Retorna a resposta com o DTO detalhado do livro com a editora incluída
        return ResponseEntity.ok().body(new DetalhesEditoraLivroDTO(livro));
    }

    // adicionar um autor a um livro
    @PutMapping("/{idLivro}/autores/{idAutor}")
    @Transactional
    public ResponseEntity<DetalhesLivroAutorDTO> adicionarAutor(@PathVariable Long idLivro, @PathVariable Long idAutor){
        // Busca o livro pelo ID
        var livro = livroRepository.getReferenceById(idLivro);
        // Busca o autor pelo ID
        var autor = autorRepository.getReferenceById(idAutor);
        // Define o autor para o livro
        livro.setAutor(autor);
        // Retorna a resposta com o DTO detalhado do livro com o autor incluído
        return ResponseEntity.ok().body(new DetalhesLivroAutorDTO(livro));
    }

    // excluir um livro
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        // Busca o livro pelo ID
        var livro = livroRepository.getReferenceById(id);
        // Busca a editora do livro pelo ID
        var editora = editoraRepository.getReferenceById(livro.getEditora().getCodigo());
        // Remove o livro da lista de livros da editora
        editora.getLivro().remove(livro);
        // Busca o autor do livro pelo ID
        var autor = autorRepository.getReferenceById(livro.getAutor().getCodigo());
        // Remove o livro da lista de livros do autor
        autor.getLivros().remove(livro);
        // Exclui o livro do banco de dados
        livroRepository.delete(livro);
        // Retorna a resposta com o código de status 204 No Content
        return ResponseEntity.noContent().build();
    }

}
