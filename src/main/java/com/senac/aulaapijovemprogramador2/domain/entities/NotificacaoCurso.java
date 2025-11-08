package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.interfaces.ICurso;
import com.senac.aulaapijovemprogramador2.domain.interfaces.INotificacao;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class NotificacaoCurso implements ICurso {

    //colunas
    private String senhaDeAcesso;

    public NotificacaoCurso(String senhaDeAcesso){
        this.senhaDeAcesso = senhaDeAcesso;
    }

    //foreght keys (FK)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    //métodos
    @Override
    public boolean publicarCurso(String senha, String nomeCurso) {
        ICurso curso = new NotificacaoCurso(senha);
        return curso.publicarCurso(senha, nomeCurso);
    }

    @Override
    public boolean inscreverAluno(String nomeAluno, String nomeCurso) {
        ICurso curso = new NotificacaoCurso(senhaDeAcesso);
        System.out.println("Aluno: " + nomeAluno + " inscrito no curso: " + nomeCurso);
        return curso.inscreverAluno(nomeAluno, nomeCurso);
    }

    @Override
    public void notificarAluno(String contato, String mensagem) {
        // Aqui dá para escolher Email ou SMS
        INotificacao notificacao = new NotificacaoEmail(contato);
        INotificacao notificacaoUm = new NotificacaoSMS(contato);
        // ou: INotificacao notificacao = new NotificacaoSMS(contato);
        notificacao.Enviar(mensagem);
        notificacaoUm.Enviar(contato);
    }
}
