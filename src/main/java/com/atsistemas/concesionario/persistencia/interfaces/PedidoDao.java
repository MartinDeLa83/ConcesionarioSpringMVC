package com.atsistemas.concesionario.persistencia.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.concesionario.entidades.Pedido;

public interface PedidoDao extends JpaRepository<Pedido, Long> {

}
