package com.senac.aulaapijovemprogramador2.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relação exemplo: um curso pode ter várias disciplinas e uma disciplina pode estar presente em vários cursos
    @ManyToMany(mappedBy = "disciplinas") // O nome do campo na entidade Curso (plural)
    private List<Curso> cursos; // Renomeado para plural
}
