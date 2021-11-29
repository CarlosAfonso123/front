package br.edu.unifio.setimoprojeto.repository;

import br.edu.unifio.setimoprojeto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
