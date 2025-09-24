package com.senac.aulaapijovemprogramador2.model.entities;

public class CursoTecnico extends Curso{

    public CursoTecnico(){}

    private String tecnologo;

    public CursoTecnico(Long id, String nomeCurso, String instrutor, boolean isPublicado,
                        String tecnologo){
        super(id,nomeCurso,instrutor,isPublicado);
        this.tecnologo = tecnologo;
    }

    public String getTecnologo() {
        return tecnologo;
    }

    public void setTecnologo(String tecnologo) {
        this.tecnologo = tecnologo;
    }
}
