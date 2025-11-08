
package com.senac.aulaapijovemprogramador2.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    //colunas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CNPJ;

    private String nomeFantasia;

    //métodos
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private List<Pedido> pedidos;

}