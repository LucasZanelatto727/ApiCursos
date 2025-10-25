package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.valueobjects.CPF;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {

    private String frequencia;
    private String nota;

    //Relação exemplo: um aluno pode cursar vários cursos e um curso pode ter vários alunos
    @ManyToMany
    @JoinColumn(name = "curso-cursa")
    private List<Curso> cursa;

    public Aluno(Long id, String nome, CPF cpf, String email, String telefone,
                 List<Curso> cursa, String frequencia, String nota) {
        super(id, nome, cpf, email, telefone);
        this.frequencia = frequencia;
        this.nota = nota;
        this.cursa = cursa;
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
