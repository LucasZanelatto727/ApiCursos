
package com.senac.aulaapijovemprogramador2.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    //colunas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String rota;

    //métodos
    public Menu(String rota, String descricao) {
        this.descricao = descricao;
        this.rota = rota;
    }
}