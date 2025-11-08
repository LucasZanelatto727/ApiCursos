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
@DiscriminatorValue("CURSO-TÉCNICO")
public class CursoTecnico extends Curso {

    //colunas
    private String tecnologo;

    //foreght keys (FK)
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    //métodos
    public CursoTecnico(Long id, String nomeCurso, String instrutor,
                        boolean isPublicado, LocalDateTime dataInicioCurso, LocalDateTime dataTerminoCurso,
                        List<Disciplina> disciplina, String tecnologo) {
        super(id, nomeCurso, instrutor,  isPublicado, dataInicioCurso, dataTerminoCurso, disciplina);
        this.tecnologo = tecnologo;
    }
}
