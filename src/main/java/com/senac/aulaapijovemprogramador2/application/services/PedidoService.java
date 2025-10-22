package com.senac.aulaapijovemprogramador2.application.services;

import com.senac.aulaapijovemprogramador2.application.dto.pedido.PedidoResponseDto;
import com.senac.aulaapijovemprogramador2.application.dto.usuario.UsuarioResponseDto;
import com.senac.aulaapijovemprogramador2.domain.repository.PedidoRepository;
import com.senac.aulaapijovemprogramador2.domain.valueobjects.EnumStatusUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoResponseDto> listarTodos() {

        return  pedidoRepository
                .findAll()
                .stream()
                .map(PedidoResponseDto::new)
                .collect(Collectors.toList());
    }
}