package com.atsistemas.concesionario.persistencia.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.concesionario.entidades.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long>{

}
