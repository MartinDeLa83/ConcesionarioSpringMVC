package com.atsistemas.concesionario.persistencia.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.concesionario.entidades.Factura;

public interface FacturaDao extends JpaRepository<Factura, Long> {

}
