package com.atsistemas.concesionario.persistencia.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.concesionario.entidades.Comercial;

public interface ComercialDao extends JpaRepository<Comercial, Long> {

}
