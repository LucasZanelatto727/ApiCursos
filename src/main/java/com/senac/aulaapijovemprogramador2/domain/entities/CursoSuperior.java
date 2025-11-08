package com.senac.aulaapijovemprogramador2.domain.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CURSO-SUPERIOR")
public class CursoSuperior extends Curso {

    //colunas
    private String creditos;
    private String bacharel;
    private String licenciatura;

    //foreght keys (FK)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    //métodos
    public CursoSuperior(Long id, String nomeCurso, String instrutor, boolean isPublicado,
                         LocalDateTime dataInicioCurso, LocalDateTime dataTerminoCurso,
                         List<Disciplina> disciplina, String creditos, String bacharel, String licenciatura) {
        super(id, nomeCurso, instrutor, isPublicado, dataInicioCurso, dataTerminoCurso, disciplina);
        this.creditos = creditos;
        this.bacharel = bacharel;
        this.licenciatura = licenciatura;
    }

}
