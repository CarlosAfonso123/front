package br.edu.unifio.setimoprojeto.repository;

import br.edu.unifio.setimoprojeto.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
