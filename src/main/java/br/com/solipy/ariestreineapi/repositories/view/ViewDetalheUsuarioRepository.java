package br.com.solipy.ariestreineapi.repositories.view;

import br.com.solipy.ariestreineapi.models.view.ViewDetalheUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewDetalheUsuarioRepository extends JpaRepository<ViewDetalheUsuario, Long>, QuerydslPredicateExecutor<ViewDetalheUsuario> {
}
