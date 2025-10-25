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
@DiscriminatorValue("CURSO-TÉCNICO")
public class CursoTecnico extends Curso {

    private String tecnologo;

    public CursoTecnico(Long id, String nomeCurso, String instrutor,
                        boolean isPublicado, String tecnologo) {
        super(id, nomeCurso, instrutor, isPublicado);
        this.tecnologo = tecnologo;
    }
}
