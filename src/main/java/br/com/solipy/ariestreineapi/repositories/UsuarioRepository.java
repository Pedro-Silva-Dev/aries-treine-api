package br.com.solipy.ariestreineapi.repositories;

import br.com.solipy.ariestreineapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario> {
    Optional<Usuario> findByUsername(String username);
}
