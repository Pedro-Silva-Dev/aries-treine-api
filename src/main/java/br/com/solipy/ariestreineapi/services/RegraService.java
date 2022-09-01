package br.com.solipy.ariestreineapi.services;

import br.com.solipy.ariestreineapi.repositories.RegraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class RegraService {

    private final RegraRepository regraRepository;

}
