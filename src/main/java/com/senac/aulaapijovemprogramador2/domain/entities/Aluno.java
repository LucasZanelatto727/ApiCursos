package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.valueobjects.CPF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
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
    @JoinTable(name = "aluno_curso",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos;

    public Aluno(Long id, String nome, CPF cpf, String email, String telefone,
                 String frequencia, String nota, List<Curso> cursos) {
        super(id, nome, cpf, email, telefone);
        this.frequencia = frequencia;
        this.nota = nota;
        this.cursos = cursos;
    }

    @Override
    public String apresentar() {
        return "Você está matriculado nos cursos de " + this.cursos + ". Sua nota é "
                + this.nota + " e sua frequência é " + this.frequencia;
    }
}
