package com.senac.aulaapijovemprogramador2.model.entities;

import com.senac.aulaapijovemprogramador2.model.valueobjects.CPF;

public class Administrador extends Usuario{

    public Administrador(Long id , String nome, CPF cpf, String email, String telefone){
        super(id,nome,cpf,email, telefone);
    }

    private boolean AcessoIrrestrito;

    private boolean acessoIrrestrito;

    public boolean isAcessoIrrestrito(){
        return AcessoIrrestrito;
    }

    public void setAcessoIrrestrito(boolean acessoIrrestrito){
        AcessoIrrestrito = acessoIrrestrito;
    }

    @Override
    public String apresentar() {
        return "Eu sou o Administrador " + this.getNome()+ "e tenho Acesso" + (this.acessoIrrestrito ? " Irrestrito!": "nenhum");
    }
}
