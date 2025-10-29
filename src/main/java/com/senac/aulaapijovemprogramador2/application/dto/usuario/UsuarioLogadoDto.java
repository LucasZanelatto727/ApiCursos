package com.senac.aulaapijovemprogramador2.application.dto.usuario;

import com.senac.aulaapijovemprogramador2.domain.entities.Usuario;

public record UsuarioLogadoDto(Long id, String email) {
    public UsuarioLogadoDto(Usuario usuario) {

        this(
                usuario.getId(),
                usuario.getEmail()
        );

    }
}