package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.interfaces.INotificacao;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class NotificacaoSMS implements INotificacao {

    //colunas
    private String telefoneDestino;

    //foreght keys (FK)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    //métodos
    public NotificacaoSMS(String telefoneDestino) {
        this.telefoneDestino = telefoneDestino;
    }

    @Override
    public void Enviar(String mensagem) {
        System.out.println("TELEFONE DESTINO: " + this.telefoneDestino + " Mensagem: " + mensagem);
    }
}
