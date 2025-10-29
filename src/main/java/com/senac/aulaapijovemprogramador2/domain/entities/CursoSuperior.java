package com.senac.aulaapijovemprogramador2.domain.entities;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CURSO-SUPERIOR")
public class CursoSuperior extends Curso {

    private String creditos;
    private String bacharel;
    private String licenciatura;

    public CursoSuperior(Long id, String nomeCurso, String instrutor, boolean isPublicado,
                         List<Disciplina> disciplina, String creditos, String bacharel, String licenciatura) {
        super(id, nomeCurso, instrutor, isPublicado, disciplina);
        this.creditos = creditos;
        this.bacharel = bacharel;
        this.licenciatura = licenciatura;
    }

}
