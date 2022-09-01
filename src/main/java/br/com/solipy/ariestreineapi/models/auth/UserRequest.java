package br.com.solipy.ariestreineapi.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {

    private String name;
    private String roles;
    private String userKey;

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s %s", this.name, this.roles, this.userKey);
    }
}
