package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.interfaces.INotificacao;

public class NotificacaoEmail implements INotificacao {

    private String emailDestino;

    public NotificacaoEmail(String emailDestino){
        this.emailDestino = emailDestino;
    }

    @Override
    public void Enviar(String mensagem) {
        System.out.println("EMAIL PARA: "+this.emailDestino+" MENSAGEM: " + mensagem);
    }
}
