package br.com.solipy.ariestreineapi.services;

import br.com.solipy.ariestreineapi.models.QUsuario;
import br.com.solipy.ariestreineapi.models.Usuario;
import br.com.solipy.ariestreineapi.repositories.UsuarioRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Page<Usuario> getUsuarioPage(String nome, Boolean status, Pageable pageable) {
        QUsuario qUsuario = QUsuario.usuario;
        BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(nome)) {
            builder.and(qUsuario.nome.toLowerCase().like(("%"+nome+"%").toLowerCase()));
        }
        if(Objects.nonNull(status)) {
            builder.and(qUsuario.isEnabled.eq(status));
        }
        Page<Usuario> page = usuarioRepository.findAll(builder, pageable);
        return page;
    }
}
