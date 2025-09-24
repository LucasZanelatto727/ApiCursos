package com.senac.aulaapijovemprogramador2.model.entities;

import com.senac.aulaapijovemprogramador2.model.valueobjects.CPF;

import java.util.List;

public class Aluno extends Usuario{

    public Aluno(){}

    private List<Curso> cursa;
    private String frequencia;
    private String nota;

    public Aluno(Long id , String nome, CPF cpf, String email, String telefone,
                 List<Curso> cursa, String frequencia, String nota){
        super(id,nome, cpf, email, telefone);
        this.cursa = cursa;
        this.frequencia = frequencia;
        this.nota = nota;

    }

    public List<Curso> getCursos() {
        return cursa;
    }

    public void setCursos(List<Curso> cursa) {
        this.cursa = cursa;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String apresentar() {
         return "Você está matriculado nos cursos de " +this.cursa+ ". Sua nota é "
                +this.nota+ " e sua frequência é " +this.frequencia;
    }
}
