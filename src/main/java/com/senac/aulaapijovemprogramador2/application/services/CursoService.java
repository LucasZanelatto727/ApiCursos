package com.senac.aulaapijovemprogramador2.application.services;

import com.senac.aulaapijovemprogramador2.application.dto.curso.CursoResponseDto;
import com.senac.aulaapijovemprogramador2.application.dto.curso.CursoRequestDto;
import com.senac.aulaapijovemprogramador2.application.dto.usuario.UsuarioCriarRequestDto;
import com.senac.aulaapijovemprogramador2.application.dto.usuario.UsuarioResponseDto;
import com.senac.aulaapijovemprogramador2.domain.entities.Curso;
import com.senac.aulaapijovemprogramador2.domain.entities.Usuario;
import com.senac.aulaapijovemprogramador2.domain.repository.CursoRepository;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusCurso;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoResponseDto> listarTodos(){

        return  cursoRepository
                .findAllByStatusNot(EnumStatusCurso.EXCLUIDO)
                .stream()
                .map(CursoResponseDto::new)
                .collect(Collectors.toList());
    }
    public CursoResponseDto buscarPorId(Long id){

        return cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO)
                .stream()
                .map(CursoResponseDto::new)
                .findFirst()
                .orElse(null);
    }

    public CursoResponseDto salvarCurso(CursoRequestDto dto) throws Exception {
        var cursoBanco = cursoRepository
                .findByIdAndStatusNot(cursoRequestDto.dto(),
                        EnumStatusCurso.EXCLUIDO)
                .orElse(new Curso(dto));

        if(cursoBanco.getId() != null){
            cursoBanco = cursoBanco
                    .atualizarCursoFromDTO(cursoBanco, cursoRequest);
        }

        cursoRepository.save(cursoBanco);
        return new CursoResponseDto(cursoBanco);
    }

    public boolean excluirCurso(Long id){
        try{
            var curso = cursoRepository.findById(id).orElse(null);

            if(curso == null){
                return false;
            }

            alterarStatusCurso(curso, EnumStatusCurso.EXCLUIDO);

            return true;
        }catch (Exception e){
            System.out.print("Erro ao excluir curso!");
            return false;
        }
    }

    public boolean bloquearCurso(Long id){

        try {

            var curso = cursoRepository.findByIdAndStatusNot(id,EnumStatusCurso.EXCLUIDO).orElse(null);

            if(curso == null){
                return false;
            }

            alterarStatusCurso(curso, EnumStatusCurso.BLOQUEADO);

            return true;
        }catch (Exception e){
            System.out.println("Erro ao bloquear curso!");
            return false;
        }
    }

    public boolean desbloquearCurso(Long id) {

        try {

            var curso = cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO).orElse(null);

            if (curso == null) {
                return false;
            }

            alterarStatusCurso(curso, EnumStatusCurso.ATIVO);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao desbloquear curso!");
            return false;
        }
    }

    public boolean publicarCurso(Long id) {

        var curso = cursoRepository.findById(id).orElse(null);

        if (curso == null) {
            return ResponseEntity.notFound().build().hasBody();
        }

        curso.setisPublicado(true);
        cursoRepository.save(curso);

        return ResponseEntity.ok().build().hasBody();
    }

    public boolean editarCurso(Long id){

        try {
            var cursoBanco = cursoRepository.findByIdAndStatusNot(id, EnumStatusCurso.EXCLUIDO).orElse(null);

            if (cursoBanco.getIsPublicado()) {
                if (!cursoBanco.getNome().equals(cursoBanco.getNome())) {
                    return ResponseEntity.badRequest().body("Não é permitido alterar o nome após a publicação").hasBody();
                }
                if (!cursoBanco.getInstrutor().equals(cursoBanco.getInstrutor())) {
                    return ResponseEntity.badRequest().body("Não é permitido alterar o instrutor após a publicação").hasBody();
                }
            }

            var cursoSave = cursoBanco.atualizarCursoFromDTO(cursoBanco, curso);

            cursoRepository.save(cursoSave);
            return ResponseEntity.ok(curso);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()).hasBody();
        }
    }


    private void alterarStatusCurso(Curso curso, EnumStatusCurso statusCurso){
        curso.setStatus(statusCurso);
        cursoRepository.save(curso);
    }
}
