package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.application.dto.usuario.UsuarioCriarRequestDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ADMIN")
public class Administrador extends Usuario {

    private boolean acessoIrrestrito;

    public Administrador(UsuarioCriarRequestDto usuarioRequest) {
        super(usuarioRequest);
        this.setAcessoIrrestrito(true);
    }

    @Override
    public String apresentar() {
        return " Dados do Administratod Nome: "+ this.getNome() +
                " CPF Format "+ this.getCpf().toString() + " Nivel de acesso Irrestrito: "+ this.isAcessoIrrestrito();
    }
}