package br.com.solipy.ariestreineapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "usuarios")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @NotEmpty
    protected String nome;

    @JsonIgnore
    @NotNull @NotEmpty
    protected String password;

    @NotNull @NotEmpty
    protected String username;

    @Column(name = "conta_ativa")
    private Boolean isAccountNonExpired = true;

    @Column(name = "conta_liberada")
    private Boolean isAccountNonLocked = true;

    @Column(name = "permissoes_ativas")
    private Boolean isCredentialsNonExpired = true;

    @Column(name = "ativo")
    private Boolean isEnabled = true;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "regra_id")
    private Regra regra;

}
