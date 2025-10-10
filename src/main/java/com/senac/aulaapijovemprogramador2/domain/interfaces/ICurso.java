package com.senac.aulaapijovemprogramador2.domain.interfaces;

public interface ICurso {

    boolean publicarCurso (String senha, String nomeCurso);

    boolean inscreverAluno(String nomeAluno, String nomeCurso);

    void notificarAluno(String contato, String mensagem);

}
