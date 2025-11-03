package com.senac.aulaapijovemprogramador2.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private String numero;

    @OneToOne
    @JoinColumn(name = "id_pedido", unique = true)
    private Pedido pedido;

}