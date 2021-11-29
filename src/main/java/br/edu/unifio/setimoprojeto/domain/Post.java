package br.edu.unifio.setimoprojeto.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotBlank(message = "O campo nome é obrigatório!!")
    @Size(min = 3, max = 25, message = "O tamanho do campo nome deve ser entre 3 e 25")
    private String titulo;

    @NotBlank(message = "O campo nome é obrigatório!!!!!!!!!!!!!")
    @Size(min = 3, max = 30, message = "O tamanho do campo nome deve ser entre 3 e 30")
    private String conteudo;

    @ManyToOne
    @NotNull(message = "O campo usuario é obrigátorio")
    private Usuario usuario;
}
