package com.senac.aulaapijovemprogramador2.controllers;

import com.senac.aulaapijovemprogramador2.dto.UsuarioCriarRequestDto;
import com.senac.aulaapijovemprogramador2.model.entities.Usuario;
import com.senac.aulaapijovemprogramador2.model.repository.UsuarioRepository;
import com.senac.aulaapijovemprogramador2.model.valueobjects.CPF;
import com.senac.aulaapijovemprogramador2.model.valueobjects.EnumStatusUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios controller",description = "Controladora responsável por gerenciar os usuários!")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Operation(summary = "Listar todos",description = "Método para listar todos os usuários!")
    public ResponseEntity<List<Usuario>> listarTodos() {

        var usuarios = usuarioRepository.findAllByStatusNot(EnumStatusUsuario.EXCLUIDO);
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Consulta de usuario por ID", description = "Médoto responsável por consultar um único usuário por ID e se não existir retorna null!")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {

            var usuario = usuarioRepository.findByIdAndStatusNot(id, EnumStatusUsuario.EXCLUIDO).orElse(null);

            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(usuario);

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Criar usuario",description = "Método responsável por criar usuário")
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioCriarRequestDto usuario){

        try {
            var usuarioBanco = usuarioRepository.findByCpf_CpfAndStatusNot(usuario.cpf(), EnumStatusUsuario.EXCLUIDO).orElse(new Usuario(usuario));

            if(usuarioBanco.getId() != null){
                usuarioBanco = usuarioBanco.atualizarUsuarioFromDTO(usuarioBanco, usuario);
            }

            usuarioRepository.save(usuarioBanco);
            return ResponseEntity.ok(usuarioBanco);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuario",description = "Método responsável por atualizar usuário")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioCriarRequestDto usuario){

        var usuarioBanco = usuarioRepository.findByIdAndStatusNot(id,EnumStatusUsuario.EXCLUIDO).orElse(null);
        if(usuarioBanco == null){
            return ResponseEntity.notFound().build();
        }

        var usuarioSave = usuarioBanco.atualizarUsuarioFromDTO(usuarioBanco, usuario);

        usuarioRepository.save(usuarioSave);
        return ResponseEntity.ok(usuarioSave);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete de usuário!",description = "Método responsável por deletar um usuário")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){

        var usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        usuario.setStatus(EnumStatusUsuario.EXCLUIDO);
        usuarioRepository.save(usuario);

        return  ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/bloquear")
    @Operation(summary = "Bloquear de usuário!",description = "Método responsável por Bloquear um usuario")
    public ResponseEntity<?> atualizarBloquear(@PathVariable Long id){

        var usuario = usuarioRepository.findByIdAndStatusNot(id,EnumStatusUsuario.EXCLUIDO).orElse(null);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        usuario.setStatus(EnumStatusUsuario.BLOQUEADO);
        usuarioRepository.save(usuario);

        return  ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/desbloquear")
    @Operation(summary = "Desbloquear de usuário!",description = "Método responsável por Desbloquear um usuario")
    public ResponseEntity<?> atualizarDesbloquear(@PathVariable Long id){

        var usuario = usuarioRepository.findByIdAndStatusNot(id,EnumStatusUsuario.EXCLUIDO).orElse(null);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        usuario.setStatus(EnumStatusUsuario.ATIVO);
        usuarioRepository.save(usuario);

        return  ResponseEntity.ok().build();
    }

}
