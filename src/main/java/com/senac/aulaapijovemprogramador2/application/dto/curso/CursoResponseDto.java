package com.senac.aulaapijovemprogramador2.application.dto.curso;

import com.senac.aulaapijovemprogramador2.domain.entities.Curso;

public record CursoResponseDto(Long id, String nomeCurso, String Instrutor,
                                 Boolean isPublicado, String nome, String senha , String status) {


    public CursoResponseDto(Curso curso){
        this(
                curso.getId(),
                curso.getNomeCurso(),
                curso.getInstrutor(),
                curso.getIsPublicado(),
                curso.getNome(),
                curso.getSenha(),
                curso.getStatus().toString()
        );
    }
}
