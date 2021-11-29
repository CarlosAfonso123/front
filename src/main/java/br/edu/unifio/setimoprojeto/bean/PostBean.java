package br.edu.unifio.setimoprojeto.bean;



import br.edu.unifio.setimoprojeto.domain.Post;
import br.edu.unifio.setimoprojeto.domain.Usuario;
import lombok.Data;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
@Data
public class PostBean {
    private Post post;
    private List<Usuario> usuarios;
    private List<Post> posts;

    public void novo() {
        post = new Post();

        RestTemplate restTemplate = new RestTemplate();
        Usuario[] vetor = restTemplate.getForObject("http://localhost:8080/usuarios", Usuario[].class);
        usuarios = Arrays.asList(vetor);
    }

    public void listar() {

        RestTemplate restTemplate = new RestTemplate();
        Post[] vetor = restTemplate.getForObject("http://localhost:8080/posts", Post[].class);
        posts = Arrays.asList(vetor);
    }

    public void salvar(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            if(post.getCodigo() == null) {
                restTemplate.postForObject("http://localhost:8080/posts", post, Post.class);
            }else {
                HttpEntity<Post> httpEntity = new HttpEntity<>(post);
                restTemplate.exchange(
                        "http://localhost:8080/posts/" + post.getCodigo(),
                        HttpMethod.PUT,
                        httpEntity,
                        Post.class
                );
            }
            Messages.addFlashGlobalInfo("Post salva com sucesso");
            Faces.navigate("post-listagem.xhtml?faces-redirect=true");
        }catch (DataIntegrityViolationException excecao) {
            Messages.addGlobalInfo("Já existe um post cadastrado igual a este!");
        }
    }

    public void excluir(){
        try {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete("http://localhost:8080/posts/" + post.getCodigo());

            Messages.addFlashGlobalInfo("Post removido com sucesso");
            Faces.navigate("post-listagem.xhtml?faces-redirect=true");
        }catch (DataIntegrityViolationException excecao) {
            Messages.addGlobalInfo("O post selecionado está vinculado com outros registros!");
        }
    }

    public void selecionarEdicao(Post post) {
        Faces.setFlashAttribute("post", post);
        Faces.navigate("post-edicao.xhtml?faces-redirect=true");
    }

    public void selecionarExclusao(Post post) {
        Faces.setFlashAttribute("post", post);
        Faces.navigate("post-exclusao.xhtml?faces-redirect=true");
    }

    public void carregar(){
        post = Faces.getFlashAttribute("post");

        RestTemplate restTemplate = new RestTemplate();
        Usuario[] vetor = restTemplate.getForObject("http://localhost:8080/usuarios", Usuario[].class);
        usuarios = Arrays.asList(vetor);

        if (post == null){
            Faces.navigate("post-listagem.xhtml?faces-redirect=true");
        }
    }



}
