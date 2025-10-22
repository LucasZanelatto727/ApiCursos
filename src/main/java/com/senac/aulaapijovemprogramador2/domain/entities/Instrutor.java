package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.domain.valueobjects.CPF;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("INSTRUTOR")
public class Instrutor extends Usuario{

    private String salario;
    private String formacao;

    // Relação exemplo: um instrutor pode lecionar vários cursos
    @OneToMany
    @JoinColumn(name = "curso_leciona")
    private List<Curso> leciona;

    public Instrutor(Long id , String nome, CPF cpf, String email, String telefone,
                     String salario, String formacao, List<Curso> leciona){
        super(id, nome, cpf, email, telefone);
        this.salario = salario;
        this.formacao = formacao;
        this.leciona = leciona;
    }
    
    @Override
    public String apresentar(){

        return "Instrutor " + this.getNome() +
                " | Formação: " + this.formacao +
                " | Salário: " + this.salario +
                " | Leciona cursos: " + (leciona != null ? leciona.size() : 0);

    }
}
