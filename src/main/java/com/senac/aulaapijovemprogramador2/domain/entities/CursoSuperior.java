package com.senac.aulaapijovemprogramador2.domain.entities;

public class CursoSuperior extends Curso{

    public CursoSuperior(){}

    private String disciplina;
    private String creditos;
    private String bacharel;
    private String licenciatura;

    public CursoSuperior(Long id, String nomeCurso, String instrutor, boolean isPublicado,
                         String disciplina, String creditos, String bacharel, String licenciatura){
        super(id,nomeCurso,instrutor,isPublicado);
        this.disciplina = disciplina;
        this.creditos = creditos;
        this.bacharel = bacharel;
        this.licenciatura = licenciatura;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getBacharel() {
        return bacharel;
    }

    public void setBacharel(String bacharel) {
        this.bacharel = bacharel;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura;
    }
}
