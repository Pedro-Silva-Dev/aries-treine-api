package br.com.solipy.ariestreineapi.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "view_detalhes_usuarios")
@Entity
public class ViewDetalheUsuario {

    @Id
    private String id;

    @NotNull @NotEmpty
    private String nome;

    @NotNull
    private Long regraId;
    private String regra;

    @NotNull
    private Boolean ativo;

}
