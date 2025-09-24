package com.senac.aulaapijovemprogramador2.controllers;

import com.senac.aulaapijovemprogramador2.dto.CursoRequestDto;
import com.senac.aulaapijovemprogramador2.model.entities.Curso;
import com.senac.aulaapijovemprogramador2.model.repository.CursoRepository;
import com.senac.aulaapijovemprogramador2.model.valueobjects.EnumStatusCurso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
@Tag(name = "Curso controller", description = "Controladora responsável pelo controle dos cursos!")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Operation(summary = "Listar todos", description = "Método para listar todos os cursos!")
    public ResponseEntity<List<Curso>> listarTodos() {

        var cursos = cursoRepository.findAllByStatusNot(EnumStatusCurso.EXCLUIDO);
        return ResponseEntity.ok(cursos);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Consulta de curso por ID", description = "Médoto responsável por consultar um único curso por ID e se não existir retorna null!")
    public ResponseEntity<?> buscarCursoPorId(@PathVariable Long id) {
        var curso = cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO).orElse(null);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(curso);

    }


    @PostMapping
    @Operation(summary = "Criar Cursos", description = "Método responsável por criar os cursos!")
    public ResponseEntity<?> criarCursos(@RequestBody CursoRequestDto curso) {

        try {
            var cursoBanco = cursoRepository.findByIdAndStatusNot(curso.id(), EnumStatusCurso.EXCLUIDO).orElse(new Curso(curso));

            if (cursoBanco.getId() != null) {
                cursoBanco = cursoBanco.atualizarCursoFromDTO(cursoBanco, curso);
            }

            cursoRepository.save(cursoBanco);
            return ResponseEntity.ok(cursoBanco);


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Método resposável por atualizar usuário!")
    public ResponseEntity<?> atualizarCurso(@PathVariable Long id, @RequestBody CursoRequestDto curso) {

        var cursoBanco = cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO).orElse(null);
        if (cursoBanco == null) {
            return ResponseEntity.notFound().build();
        }

        var cursoSave = cursoBanco.atualizarCursoFromDTO(cursoBanco, curso);

        cursoRepository.save(cursoSave);
        return ResponseEntity.ok(cursoSave);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete de curso!", description = "Método responsável por deletar um curso!")
    public ResponseEntity<?> deletarCurso(@PathVariable Long id) {

        var curso = cursoRepository.findById(id).orElse(null);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        curso.setStatus(EnumStatusCurso.EXCLUIDO);
        cursoRepository.save(curso);

        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/bloquear")
    @Operation(summary = "Bloquear de curso!", description = "Método responsável por Bloquear um curso!")
    public ResponseEntity<?> atualizarBloquear(@PathVariable Long id) {

        var curso = cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO).orElse(null);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        curso.setStatus(EnumStatusCurso.BLOQUEADO);
        cursoRepository.save(curso);

        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/desbloquear")
    @Operation(summary = "Desbloquear de curso!", description = "Método responsável por Desbloquear um curso!")
    public ResponseEntity<?> atualizarDesbloquear(@PathVariable Long id) {

        var curso = cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO).orElse(null);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        curso.setStatus(EnumStatusCurso.ATIVO);
        cursoRepository.save(curso);

        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/publicar")
    @Operation(summary = "Publicar um curso!", description = "Método responsável por Publicar um curso (isPublicado = true)")
    public ResponseEntity<?> publicarCurso(@PathVariable Long id) {

        var curso = cursoRepository.findById(id).orElse(null);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        curso.setisPublicado(true);
        cursoRepository.save(curso);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/editar")
    @Operation(summary = "Impedir a edição de um curso publicado!", description = "Método responsável por impedir a edição de um curso já publicado")
    public ResponseEntity<?> editarCurso(@PathVariable Long id, @RequestBody CursoRequestDto curso) {

        try {
            var cursoBanco = cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO).orElse(null);

            if (cursoBanco.getIsPublicado()) {
                if (!cursoBanco.getNome().equals(cursoBanco.getNome())) {
                    return ResponseEntity.badRequest().body("Não é permitido alterar o nome após a publicação");
                }
                if (!cursoBanco.getInstrutor().equals(cursoBanco.getInstrutor())) {
                    return ResponseEntity.badRequest().body("Não é permitido alterar o instrutor após a publicação");
                }
            }

            var cursoSave = cursoBanco.atualizarCursoFromDTO(cursoBanco, curso);

            cursoRepository.save(cursoSave);
            return ResponseEntity.ok(curso);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}



