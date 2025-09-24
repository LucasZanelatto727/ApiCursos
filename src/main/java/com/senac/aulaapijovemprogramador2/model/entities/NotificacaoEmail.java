package com.senac.aulaapijovemprogramador2.model.entities;

import com.senac.aulaapijovemprogramador2.model.interfaces.INotificacao;

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
