package br.com.solipy.ariestreineapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "respostas")
@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty
    private String nome;

    private Boolean ativo = true;

    @NotNull
    private BigDecimal pontos;

    @NotNull
    @Column(name = "pergunta_id")
    private Long perguntaId;

}
