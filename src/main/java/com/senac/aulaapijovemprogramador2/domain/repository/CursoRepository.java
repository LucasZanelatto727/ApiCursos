package com.senac.aulaapijovemprogramador2.domain.repository;


import com.senac.aulaapijovemprogramador2.domain.entities.Curso;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByIdAndStatusNot(Long id, EnumStatusCurso status);

    // Lista todos ignorando status EXCLUIDO
    List<Curso> findAllByStatusNot(EnumStatusCurso status);
    }
