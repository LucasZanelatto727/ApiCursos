package com.senac.aulaapijovemprogramador2.domain.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CursoTecnico extends Curso {

    private String tecnologo;

    public CursoTecnico(Long id, String nomeCurso, String instrutor,
                        boolean isPublicado, String tecnologo) {
        super(id, nomeCurso, instrutor, isPublicado);
        this.tecnologo = tecnologo;
    }
}
