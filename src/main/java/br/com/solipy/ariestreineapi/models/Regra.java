package br.com.solipy.ariestreineapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "regras")
@Entity
public class Regra {

    @Id
    private Long id;
    private String nome;
    private Boolean ativo;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regra")
    private List<Usuario> usuarios;
}
