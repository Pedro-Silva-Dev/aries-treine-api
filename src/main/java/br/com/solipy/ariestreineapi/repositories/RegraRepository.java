package br.com.solipy.ariestreineapi.repositories;

import br.com.solipy.ariestreineapi.models.Regra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraRepository extends JpaRepository<Regra, Long>, QuerydslPredicateExecutor<Regra> {
}
