package br.com.solipy.ariestreineapi.services;

import br.com.solipy.ariestreineapi.models.QUsuario;
import br.com.solipy.ariestreineapi.models.Regra;
import br.com.solipy.ariestreineapi.models.Usuario;
import br.com.solipy.ariestreineapi.models.form.AtualizarSenhaForm;
import br.com.solipy.ariestreineapi.models.form.UsuarioForm;
import br.com.solipy.ariestreineapi.models.view.QViewDetalheUsuario;
import br.com.solipy.ariestreineapi.models.view.ViewDetalheUsuario;
import br.com.solipy.ariestreineapi.repositories.UsuarioRepository;
import br.com.solipy.ariestreineapi.repositories.view.ViewDetalheUsuarioRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ViewDetalheUsuarioRepository viewDetalheUsuarioRepository;

    public Page<ViewDetalheUsuario> obterPaginaUsuario(String nome, Boolean status, Long regraId, Pageable pageable) {
        QViewDetalheUsuario qViewDetalheUsuario = QViewDetalheUsuario.viewDetalheUsuario;
        BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(nome)) {
            builder.and(qViewDetalheUsuario.nome.toLowerCase().like(("%"+nome+"%").toLowerCase()));
        }
        if(Objects.nonNull(status)) {
            builder.and(qViewDetalheUsuario.ativo.eq(status));
        }
        if(Objects.nonNull(regraId)) {
            builder.and(qViewDetalheUsuario.regraId.eq(regraId));
        }
        return viewDetalheUsuarioRepository.findAll(builder, pageable);
    }

    public List<ViewDetalheUsuario> obterListaUsuario(String nome, Boolean status, Long regraId) {
        QViewDetalheUsuario qViewDetalheUsuario = QViewDetalheUsuario.viewDetalheUsuario;
        BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(nome)) {
            builder.and(qViewDetalheUsuario.nome.toLowerCase().like(("%"+nome+"%").toLowerCase()));
        }
        if(Objects.nonNull(status)) {
            builder.and(qViewDetalheUsuario.ativo.eq(status));
        }
        if(Objects.nonNull(regraId)) {
            builder.and(qViewDetalheUsuario.regraId.eq(regraId));
        }
        return (List<ViewDetalheUsuario>) viewDetalheUsuarioRepository.findAll(builder);
    }

    public Usuario criarUsuario(UsuarioForm usuarioForm) {
        String password = new BCryptPasswordEncoder().encode(usuarioForm.getPassword());
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuarioForm.getNome());
        novoUsuario.setUsername(usuarioForm.getUsername());
        novoUsuario.setPassword(password);
        novoUsuario.setRegra(new Regra(usuarioForm.getRegraId()));
        return usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizarUsuario(Long id, ViewDetalheUsuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()) {
            Usuario usuarioSavo = optionalUsuario.get();
            usuarioSavo.setNome(usuario.getNome());
            usuarioSavo.setRegra(new Regra(usuario.getRegraId()));
            usuarioSavo.setIsEnabled(usuario.getAtivo());
            return usuarioRepository.save(usuarioSavo);
        }
        return null;
    }

    public Map<String, Boolean> atualizarSenha(Long userId, AtualizarSenhaForm atualizarSenhaForm) {
        Map<String, Boolean> status = new HashMap<String, Boolean>();
        status.put("sucesso", false);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(userId);
        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            boolean senhaValida = new BCryptPasswordEncoder().matches(atualizarSenhaForm.getSenhaAtual(), usuario.getPassword());
            if(senhaValida) {
                String novaSenha = new BCryptPasswordEncoder().encode(atualizarSenhaForm.getNovaSenha());
                Integer senhaAtualizada = usuarioRepository.updateSenhaById(novaSenha, usuario.getId());
                status.put("sucesso", senhaAtualizada > 0);
            }
        }
        return status;
    }
}
