package br.com.solipy.ariestreineapi.services.auth;

import br.com.solipy.ariestreineapi.models.Usuario;
import br.com.solipy.ariestreineapi.models.auth.UserDetail;
import br.com.solipy.ariestreineapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = usuarioRepository.findByUsername(username);
        if(optionalUser.isEmpty()) {
            return new UserDetail();
        }
        Usuario usuario = optionalUser.get();
        UserDetail userDetail = new UserDetail(usuario);
        return userDetail;
    }


    public Boolean comparePassword(String password, String encodePassword) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        Boolean valid = bCrypt.matches(password, encodePassword);
        return valid;
    }
}
