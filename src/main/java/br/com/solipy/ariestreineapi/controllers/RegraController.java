package br.com.solipy.ariestreineapi.controllers;

import br.com.solipy.ariestreineapi.services.RegraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regra")
@Log4j2
@RequiredArgsConstructor
public class RegraController {

    private final RegraService regraService;

}
