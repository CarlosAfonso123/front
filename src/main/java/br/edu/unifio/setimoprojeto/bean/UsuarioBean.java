package br.edu.unifio.setimoprojeto.bean;

import br.edu.unifio.setimoprojeto.domain.Usuario;
import br.edu.unifio.setimoprojeto.repository.UsuarioRepository;
import lombok.Data;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

@Component
@ViewScoped
@Data
public class UsuarioBean {
    private Usuario usuario;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void novo(){
        usuario = new Usuario();
    }

    public void salvar(){
        try {
            usuarioRepository.save(usuario);
            Messages.addGlobalInfo("Usuário salvo com sucesso");
            novo();
        }catch (DataIntegrityViolationException excecao) {//proibir nome igual
            Messages.addGlobalInfo("Já existe um usuário para o nome informado!");
        }
    }
}
