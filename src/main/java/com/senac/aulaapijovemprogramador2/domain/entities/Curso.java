package com.senac.aulaapijovemprogramador2.domain.entities;

import com.senac.aulaapijovemprogramador2.application.dto.curso.CursoRequestDto;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Curso(Long id, String nomeCurso, String instrutor, boolean isPublicado) {
        this.setId(id);
        this.setNomeCurso(nomeCurso);
        this.setInstrutor(instrutor);
        this.setisPublicado(isPublicado);
    }

    public Curso(CursoRequestDto curso) {
        this.nome = curso.nome();
        this.nomeCurso = curso.nomeCurso();
        this.instrutor = curso.instrutor();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCurso;

    private String instrutor;

    private boolean isPublicado;

    private String nome;

    private String senha;

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

    public Curso atualizarCursoFromDTO(Curso cursoBanco, CursoRequestDto dto) {

        cursoBanco.setNome(dto.nome());
        cursoBanco.setNomeCurso(dto.nomeCurso());
        cursoBanco.setInstrutor(dto.instrutor());
        return cursoBanco;

    }

    public String apresentar() {

        return "Você está matriculado no curso de "
                + this.nomeCurso + " com o instrutor " + this.instrutor;

    }
}