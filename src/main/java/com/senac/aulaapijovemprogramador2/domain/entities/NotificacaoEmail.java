package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.interfaces.INotificacao;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class NotificacaoEmail implements INotificacao {

    //colunas
    private String emailDestino;

    //foreght keys (FK)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    //métodos
    public NotificacaoEmail(String emailDestino){
        this.emailDestino = emailDestino;
    }

    @Override
    public void Enviar(String mensagem) {
        System.out.println("EMAIL PARA: "+this.emailDestino+" MENSAGEM: " + mensagem);
    }
}
