package com.senac.aulaapijovemprogramador2.model.entities;

import com.senac.aulaapijovemprogramador2.model.valueobjects.CPF;

import java.util.ArrayList;
import java.util.List;

public class Instrutor extends Usuario{

    public Instrutor(){}

    private String salario;
    private List<Curso> leciona;
    private String formacao;

    public Instrutor(Long id , String nome, CPF cpf, String email, String telefone,
                     String salario, List<Curso> leciona, String formacao){
        super(id, nome, cpf, email, telefone);
        this.salario = salario;
        this.leciona = leciona;
        this.formacao = formacao;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public List<Curso> getLeciona() {
        return leciona;
    }

    public void setLeciona(List<Curso> leciona) {
        this.leciona = leciona;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    
    @Override
    public String apresentar(){

        return "Instrutor, você leciona para o curso de " +this.leciona+ ". Sua formação é em "
                +this.formacao+ " e seu salário é de " +this.salario;

    }
}
