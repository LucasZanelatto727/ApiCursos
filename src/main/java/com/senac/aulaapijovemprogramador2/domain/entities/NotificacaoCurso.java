package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.interfaces.ICurso;
import com.senac.aulaapijovemprogramador2.domain.interfaces.INotificacao;

public class NotificacaoCurso implements ICurso {

    private String senhaDeAcesso;

    public NotificacaoCurso(String senhaDeAcesso){
        this.senhaDeAcesso = senhaDeAcesso;
    }

    @Override
    public boolean publicarCurso(String senha, String nomeCurso) {
        ICurso curso = new NotificacaoCurso(senha);
        return curso.publicarCurso(senha, nomeCurso);
    }

    @Override
    public boolean inscreverAluno(String nomeAluno, String nomeCurso) {
        System.out.println("Aluno " + nomeAluno + " inscrito no curso: " + nomeCurso);
        return true;
    }

    @Override
    public void notificarAluno(String contato, String mensagem) {
        // Aqui dá para escolher Email ou SMS
        INotificacao notificacao = new NotificacaoEmail(contato);
        // ou: INotificacao notificacao = new NotificacaoSMS(contato);
        notificacao.Enviar(mensagem);
    }
}
