package br.com.solipy.ariestreineapi.models.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    private String username;
    private String password;
}
