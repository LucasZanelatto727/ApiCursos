package com.senac.aulaapijovemprogramador2.model.entities;

import com.senac.aulaapijovemprogramador2.model.interfaces.INotificacao;

public class NotificacaoSMS implements INotificacao {

    private String telefoneDestino;

    public NotificacaoSMS(String telefoneDestino){
        this.telefoneDestino = telefoneDestino;
    }

    @Override
    public void Enviar(String mensagem) {
        System.out.println("TELEFONE DESTINO: "+this.telefoneDestino+" Mensagem: "+mensagem);
    }
}
