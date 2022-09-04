package br.com.solipy.ariestreineapi.controllers;

import br.com.solipy.ariestreineapi.models.Regra;
import br.com.solipy.ariestreineapi.services.RegraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/regra")
@Log4j2
@RequiredArgsConstructor
public class RegraController {

    private final RegraService regraService;

    @GetMapping("/lista.json")
    public ResponseEntity<List<Regra>> obterListaRegra(@RequestParam(required = false) String nome, @RequestParam(required = false) Boolean status) {
        List<Regra> list = regraService.obterListaRegra(nome, status);
        return ResponseEntity.status(HttpStatus.OK.value()).body(list);
    }

    @PostMapping("/criar.json")
    public ResponseEntity<Regra> criarRegra(@Valid @RequestBody Regra regra) {
        Regra save = regraService.criarRegra(regra);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(save);
    }

    @PutMapping("/{id}.json")
    public ResponseEntity<Regra> atualizarRegra(@PathVariable Long id, @RequestBody Regra regra) {
        Regra save = regraService.atualizarRegra(id, regra);
        return Objects.nonNull(save) ? ResponseEntity.status(HttpStatus.CREATED.value()).body(save) : ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(null);
    }

}
