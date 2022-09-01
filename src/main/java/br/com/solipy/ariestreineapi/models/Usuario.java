package br.com.solipy.ariestreineapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "usuarios")
@Entity
public class Usuario {

    @Id
    protected Long id;
    protected String nome;

    @JsonIgnore
    protected String password;
    protected String username;

    @Column(name = "conta_ativa")
    private Boolean isAccountNonExpired;

    @Column(name = "conta_liberada")
    private Boolean isAccountNonLocked;

    @Column(name = "permissoes_ativas")
    private Boolean isCredentialsNonExpired;

    @Column(name = "ativo")
    private Boolean isEnabled;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "regra_id")
    private Regra regra;

}
