package br.com.solipy.ariestreineapi.repositories;

import br.com.solipy.ariestreineapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario> {
    Optional<Usuario> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE usuarios u SET password =?1 WHERE u.id =?2", nativeQuery = true)
    Integer updateSenhaById(String novaSenha, Long usuarioId);
}
