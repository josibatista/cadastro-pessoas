


package com.cp.data.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

/**
 *
 * @author utfpr
 */
@Entity
@Data
public class Cidade implements Serializable {


    public Cidade(){
        //metodo exigido pela entity
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;


    @OneToMany(cascade=ALL, mappedBy="cidade")
   // @JsonBackReference
    private Set<Pessoa> pessoas;

}
