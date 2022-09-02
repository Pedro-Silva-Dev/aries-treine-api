package br.com.solipy.ariestreineapi.services;

import br.com.solipy.ariestreineapi.models.QRegra;
import br.com.solipy.ariestreineapi.models.Regra;
import br.com.solipy.ariestreineapi.repositories.RegraRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class RegraService {

    private final RegraRepository regraRepository;

    public List<Regra> getRegraList(String nome, Boolean status) {
        QRegra qRegra = QRegra.regra;
        BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(nome)) {
            builder.and(qRegra.nome.toLowerCase().like(("%"+nome+"%").toLowerCase()));
        }
        if(Objects.nonNull(status)) {
            builder.and(qRegra.ativo.eq(status));
        }
        return (List<Regra>) regraRepository.findAll(builder);
    }

    public Regra createRegra(Regra regra) {
        return regraRepository.save(regra);
    }

    public Regra updateRegra(Long id, Regra regra) {
        Optional<Regra> optionalRegra = regraRepository.findById(id);
        if(optionalRegra.isPresent()) {
            Regra newRegra = optionalRegra.get();
            newRegra.setNome(regra.getNome());
            newRegra.setAtivo(regra.getAtivo());
            return regraRepository.save(newRegra);
        }
        return null;
    }
}
