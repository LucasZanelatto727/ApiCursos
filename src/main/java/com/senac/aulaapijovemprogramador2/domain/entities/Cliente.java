package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.valueobjects.CPF;

import java.math.BigDecimal;

public class Cliente extends Usuario {

    public Cliente() {
    }

    public Cliente(Long id , String nome, CPF cpf, String email, BigDecimal valorConsumido, String telefone) {
        //super(id,nome,cpf,email , telefone);

        this.setValorConsumido(valorConsumido);
    }
    private BigDecimal valorConsumido;

    public BigDecimal getValorConsumido(){
        return valorConsumido;
    }

    public void setValorConsumido(BigDecimal valorConsumido) {
        this.valorConsumido = valorConsumido;
    }

    @Override
    public String apresentar(){
        String resposta = "";
        resposta +=super.apresentar() + "Valor de Consumo: " + this.valorConsumido.toString();
        return resposta;
    }
}
