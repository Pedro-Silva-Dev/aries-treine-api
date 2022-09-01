package br.com.solipy.ariestreineapi.controllers;

import br.com.solipy.ariestreineapi.models.Usuario;
import br.com.solipy.ariestreineapi.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/index.json")
    public ResponseEntity<Page<Usuario>> getUsuarioPage(@RequestParam(required = false) String nome, @RequestParam(required = false) Boolean status, Pageable pageable) {
        Page<Usuario> page = usuarioService.getUsuarioPage(nome, status, pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(page);
    }

    @GetMapping("/list.json")
    public ResponseEntity<Page<Usuario>> getUsuarioList(@RequestParam(required = false) String nome, @RequestParam(required = false) Boolean status, Pageable pageable) {
        Page<Usuario> page = usuarioService.getUsuarioPage(nome, status, pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(page);
    }

}
