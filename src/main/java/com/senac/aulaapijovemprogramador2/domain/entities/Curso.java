package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.application.dto.curso.CursoRequestDto;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "curso")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_curso", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("CURSO")
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    //colunas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCurso;

    private String instrutor;

    private boolean isPublicado;

    @Column(name="tipo_curso", insertable = false, updatable = false,nullable = true)
    private String tipo_curso;

    private EnumStatusCurso status = EnumStatusCurso.ATIVO;

    private LocalDateTime dataInicioCurso;

    private LocalDateTime dataTerminoCurso;

    //foreghts keys (FK)

    @ManyToMany
    @JoinTable(
            name = "curso_disciplina", // Nome da nova tabela de junção
            joinColumns = @JoinColumn(name = "curso_id"), // FK que referencia esta entidade (Curso)
            inverseJoinColumns = @JoinColumn(name = "disciplina_id") // FK que referencia a outra entidade (Disciplina)
    )
    private List<Disciplina> disciplinas; // Renomeado para plural, que é mais descritivo

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    //métodos

    public boolean getIsPublicado() {
        return isPublicado;
    }

    public void setisPublicado(boolean isPublicado) {
        this.isPublicado = isPublicado;
    }

    public Curso(Long id, String nomeCurso, String instrutor, boolean isPublicado,
                 LocalDateTime dataInicioCurso, LocalDateTime dataTerminoCurso, List<Disciplina> disciplinas) {
        this.setId(id);
        this.setNomeCurso(nomeCurso);
        this.setInstrutor(instrutor);
        this.setisPublicado(isPublicado);
        this.setDataInicioCurso(dataInicioCurso);
        this.setDataTerminoCurso(dataTerminoCurso);
        this.setDisciplinas(disciplinas);
    }

    public Curso(CursoRequestDto curso) {
        this.nomeCurso = curso.nomeCurso();
        this.instrutor = curso.instrutor();
    }

    public Curso atualizarCursoFromDTO(Curso cursoBanco, CursoRequestDto dto) {

        cursoBanco.setNomeCurso(dto.nomeCurso());
        cursoBanco.setInstrutor(dto.instrutor());
        return cursoBanco;

    }

    public String apresentarAluno() {

        return "Você está matriculado no curso de "
                + this.nomeCurso + " com o instrutor " + this.instrutor;

    }
}