package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.application.dto.curso.CursoRequestDto;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "curso")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_curso", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("CURSO")
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCurso;

    private String instrutor;

    private boolean isPublicado;

    @ManyToMany
    @JoinTable(
            name = "curso_disciplina", // Nome da nova tabela de junção
            joinColumns = @JoinColumn(name = "curso_id"), // FK que referencia esta entidade (Curso)
            inverseJoinColumns = @JoinColumn(name = "disciplina_id") // FK que referencia a outra entidade (Disciplina)
    )
    private List<Disciplina> disciplinas; // Renomeado para plural, que é mais descritivo

    @Column(name="tipo_curso", insertable = false, updatable = false,nullable = true)
    private String tipo_curso;

    private EnumStatusCurso status = EnumStatusCurso.ATIVO;

    @ManyToMany
    @JoinTable(
            name = "menu_curso",
            joinColumns = @JoinColumn( name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private Set<Menu> menuAcesso;

    public boolean getIsPublicado() {
        return isPublicado;
    }

    public void setisPublicado(boolean isPublicado) {
        this.isPublicado = isPublicado;
    }

    public Curso(Long id, String nomeCurso, String instrutor, boolean isPublicado, List<Disciplina> disciplinas) {
        this.setId(id);
        this.setNomeCurso(nomeCurso);
        this.setInstrutor(instrutor);
        this.setisPublicado(isPublicado);
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

    public String apresentar() {

        return "Você está matriculado no curso de "
                + this.nomeCurso + " com o instrutor " + this.instrutor;

    }
}