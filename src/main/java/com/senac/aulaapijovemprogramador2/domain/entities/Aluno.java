package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.valueobjects.CPF;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends Usuario {

    private List<Curso> cursa;
    private String frequencia;
    private String nota;

    public Aluno(Long id, String nome, CPF cpf, String email, String telefone,
                 List<Curso> cursa, String frequencia, String nota) {
        super(id, nome, cpf, email, telefone);
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

    @Override
    public String apresentar() {
        return "Você está matriculado nos cursos de " + this.cursa + ". Sua nota é "
                + this.nota + " e sua frequência é " + this.frequencia;
    }
}
