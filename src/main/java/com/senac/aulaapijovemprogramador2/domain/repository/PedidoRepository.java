package com.senac.aulaapijovemprogramador2.domain.repository;

import com.senac.aulaapijovemprogramador2.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}