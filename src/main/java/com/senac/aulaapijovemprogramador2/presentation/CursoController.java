package com.senac.aulaapijovemprogramador2.presentation;

import com.senac.aulaapijovemprogramador2.application.dto.curso.CursoRequestDto;
import com.senac.aulaapijovemprogramador2.application.dto.curso.CursoResponseDto;
import com.senac.aulaapijovemprogramador2.application.services.CursoService;

import com.senac.aulaapijovemprogramador2.domain.repository.CursoRepository;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusCurso;
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
    private CursoService cursoService;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Operation(summary = "Listar todos", description = "Método para listar todos os cursos!")
    public ResponseEntity<List<CursoResponseDto>> listarTodos() {

        return ResponseEntity.ok(cursoService.listarTodos());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Consulta de curso por ID", description = "Médoto responsável por consultar um único curso por ID e se não existir retorna null!")
    public ResponseEntity<?> buscarCursoPorId(@PathVariable Long id) {
        try {

            var curso = cursoService.buscarPorId(id);

            if(curso == null){
                return ResponseEntity.notFound().build();
            }

            return  ResponseEntity.ok(curso);

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping
    @Operation(summary = "Criar Cursos", description = "Método responsável por criar os cursos!")
    public ResponseEntity<?> criarCursos(@PathVariable Long id, @RequestBody CursoRequestDto curso) {

        try {
            var cursoSalvo = cursoService.salvarCurso(curso);
            return ResponseEntity.ok(cursoSalvo);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Método resposável por atualizar usuário!")
    public ResponseEntity<?> atualizarCurso(@PathVariable Long id, @RequestBody CursoRequestDto curso) {

        try {
            var cursoSalvo = cursoService.salvarCurso(curso);
            return ResponseEntity.ok(cursoSalvo);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete de curso!", description = "Método responsável por deletar um curso!")
    public ResponseEntity<?> deletarCurso(@PathVariable Long id) {

        return cursoService.excluirCurso(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }


    @PatchMapping("/{id}/bloquear")
    @Operation(summary = "Bloquear de curso!", description = "Método responsável por Bloquear um curso!")
    public ResponseEntity<?> atualizarBloquear(@PathVariable Long id) {

        return cursoService.bloquearCurso(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/desbloquear")
    @Operation(summary = "Desbloquear de curso!", description = "Método responsável por Desbloquear um curso!")
    public ResponseEntity<?> atualizarDesbloquear(@PathVariable Long id) {

        return cursoService.desbloquearCurso(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }


    @PatchMapping("/{id}/publicar")
    @Operation(summary = "Publicar um curso!", description = "Método responsável por Publicar um curso (isPublicado = true)")
    public ResponseEntity<?> publicarCurso(@PathVariable Long id) {

        return cursoService.publicarCurso(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
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



