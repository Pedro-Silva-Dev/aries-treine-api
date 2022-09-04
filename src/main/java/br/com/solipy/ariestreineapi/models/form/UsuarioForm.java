package br.com.solipy.ariestreineapi.models.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UsuarioForm {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    private String password;

    @NotNull @NotEmpty
    private String username;

    @NotNull
    private Long regraId;
}
