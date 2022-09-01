package br.com.solipy.ariestreineapi.models.auth;

import br.com.solipy.ariestreineapi.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Log4j2
public class UserDetail extends Usuario implements UserDetails {

    private List<String> authorities = new ArrayList<>();
    private Boolean isAccountNonExpired = true;
    private Boolean isAccountNonLocked = true;
    private Boolean isCredentialsNonExpired = true;
    private Boolean isEnabled = true;


    public UserDetail(Usuario user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Arrays.asList(user.getRegra().getNome());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> userAuthorities = authorities.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return userAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}

