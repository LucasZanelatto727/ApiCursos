package com.senac.aulaapijovemprogramador2.model.entities;

import com.senac.aulaapijovemprogramador2.dto.CursoRequestDto;
import com.senac.aulaapijovemprogramador2.model.valueobjects.EnumStatusCurso;
import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso{

    public Curso(){}

    public Curso(Long id , String nomeCurso, String instrutor, boolean isPublicado){
        this.setId(id);
        this.setNomeCurso(nomeCurso);
        this.setInstrutor(instrutor);
        this.setisPublicado(isPublicado);
    }

    public Curso (CursoRequestDto curso){
        this.nome =curso.nome();
        this.senha = curso.senha();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCurso;
    private String instrutor;
    private boolean isPublicado;
    private String nome;
    private String senha;
    private EnumStatusCurso status = EnumStatusCurso.ATIVO;

    public Long getId(){
        return id;
    }

    public void setId (Long id){
        this.id = id;
    }

    public String getNomeCurso(){
        return nomeCurso;
    }

    public void setNomeCurso (String nomeCurso){
        this.nomeCurso = nomeCurso;
    }

    public String getInstrutor(){
        return instrutor;
    }

    public void setInstrutor (String instrutor){
        this.instrutor = instrutor;
    }

    public boolean getIsPublicado(){
        return isPublicado;
    }

    public void setisPublicado (boolean isPublicado){
        this.isPublicado = isPublicado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumStatusCurso getStatus() {
        return status;
    }

    public void setStatus(EnumStatusCurso status) {
        this.status = status;
    }

    public Curso atualizarCursoFromDTO(Curso cursoBanco, CursoRequestDto cursoRequestDto){
        cursoBanco.setNome(cursoRequestDto.nome());
        cursoBanco.setSenha(cursoRequestDto.senha());
        return cursoBanco;
    }

    public String apresentar(){

        return "Você está matriculado no curso de "
                +this.nomeCurso+ " com o instrutor " +this.instrutor;

    }
}