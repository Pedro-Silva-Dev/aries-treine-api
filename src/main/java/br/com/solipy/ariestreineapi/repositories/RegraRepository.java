package br.com.solipy.ariestreineapi.repositories;

import br.com.solipy.ariestreineapi.models.Regra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegraRepository extends JpaRepository<Regra, Long>, QuerydslPredicateExecutor<Regra> {

    @Query(value = "SELECT * FROM regras", nativeQuery = true)
    List<Regra> findAll();

    @Query(value = "SELECT * FROM regras WHERE id = ?1", nativeQuery = true)
    Optional<Regra> findById(Long id);

}
