package br.com.solipy.ariestreineapi.controllers;

import br.com.solipy.ariestreineapi.models.Usuario;
import br.com.solipy.ariestreineapi.models.form.AtualizarSenhaForm;
import br.com.solipy.ariestreineapi.models.form.UsuarioForm;
import br.com.solipy.ariestreineapi.models.view.ViewDetalheUsuario;
import br.com.solipy.ariestreineapi.services.UsuarioService;
import br.com.solipy.ariestreineapi.services.auth.CurrentUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuario")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    private final CurrentUserService currentUserService;
    private final UsuarioService usuarioService;

    @GetMapping("/index.json")
    public ResponseEntity<Page<ViewDetalheUsuario>> obterPaginaUsuario(@RequestParam(required = false) String nome, @RequestParam(required = false) Boolean status, @RequestParam(required = false) Long regraId, Pageable pageable) {
        Page<ViewDetalheUsuario> page = usuarioService.obterPaginaUsuario(nome, status, regraId, pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(page);
    }

    @GetMapping("/lista.json")
    public ResponseEntity<List<ViewDetalheUsuario>> obterListaUsuario(@RequestParam(required = false) String nome, @RequestParam(required = false) Boolean status, @RequestParam(required = false) Long regraId) {
        List<ViewDetalheUsuario> list = usuarioService.obterListaUsuario(nome, status, regraId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(list);
    }

    @PostMapping("/criar.json")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioForm usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return Objects.nonNull(novoUsuario) ? ResponseEntity.status(HttpStatus.CREATED.value()).body(novoUsuario) : ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(null);
    }

    @PutMapping("/{id}.json")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid ViewDetalheUsuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
        return Objects.nonNull(usuarioAtualizado) ? ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(usuarioAtualizado) : ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(null);
    }

    @PutMapping("/atualizar-senha.json")
    public ResponseEntity<Map<String, Boolean>> atualizarSenha(@RequestBody @Valid AtualizarSenhaForm atualizarSenhaForm) {
        Map<String, Boolean> atualizarSenha = usuarioService.atualizarSenha(currentUserService.getId(), atualizarSenhaForm);
        return atualizarSenha.get("sucesso") ? ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(atualizarSenha) : ResponseEntity.status(HttpStatus.OK.value()).body(atualizarSenha);
    }

}
