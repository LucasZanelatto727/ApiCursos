package com.senac.aulaapijovemprogramador2.domain.entities;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CURSO-SUPERIOR")
public class CursoSuperior extends Curso {

    private String disciplina;
    private String creditos;
    private String bacharel;
    private String licenciatura;

    public CursoSuperior(Long id, String nomeCurso, String instrutor, boolean isPublicado,
                         String disciplina, String creditos, String bacharel, String licenciatura) {
        super(id, nomeCurso, instrutor, isPublicado);
        this.disciplina = disciplina;
        this.creditos = creditos;
        this.bacharel = bacharel;
        this.licenciatura = licenciatura;
    }

}
