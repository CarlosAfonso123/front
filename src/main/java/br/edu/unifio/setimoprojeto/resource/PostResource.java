package br.edu.unifio.setimoprojeto.resource;

import br.edu.unifio.setimoprojeto.domain.Post;
import br.edu.unifio.setimoprojeto.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostResource {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> listar() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.ASC, "codigo"));
        return posts;
    }

    @PostMapping
    public Post inserir (@RequestBody Post post) {
        Post postSalvo = postRepository.save(post);
        return postSalvo;
    }

    @DeleteMapping("/{codigo}")
    public void excluir (@PathVariable Integer codigo) {
        postRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo")
    public Post editar (@PathVariable Integer codigo, @RequestBody Post postEntrada) {
        Optional<Post> opcional = postRepository.findById(codigo);
        Post postSaida = opcional.get();

        postSaida.setConteudo(postEntrada.getConteudo());
        postSaida.setTitulo(postEntrada.getTitulo());



        Post postSalvo = postRepository.save(postSaida);
        return postSalvo;
    }
}
