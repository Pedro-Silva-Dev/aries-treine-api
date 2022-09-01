package br.com.solipy.ariestreineapi.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegra is a Querydsl query type for Regra
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegra extends EntityPathBase<Regra> {

    private static final long serialVersionUID = 354567025L;

    public static final QRegra regra = new QRegra("regra");

    public final BooleanPath ativo = createBoolean("ativo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final ListPath<Usuario, QUsuario> usuarios = this.<Usuario, QUsuario>createList("usuarios", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public QRegra(String variable) {
        super(Regra.class, forVariable(variable));
    }

    public QRegra(Path<? extends Regra> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegra(PathMetadata metadata) {
        super(Regra.class, metadata);
    }

}

