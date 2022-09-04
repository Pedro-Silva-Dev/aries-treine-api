package br.com.solipy.ariestreineapi.models.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AtualizarSenhaForm {

    @NotNull @NotEmpty
    private String senhaAtual;
    @NotNull @NotEmpty
    private String novaSenha;

}
