package com.atsistemas.concesionario.persistencia.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.concesionario.entidades.Vehiculo;

public interface VehiculoDao extends JpaRepository<Vehiculo, Long> {

}
