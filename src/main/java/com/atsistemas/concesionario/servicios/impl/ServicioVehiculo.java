package com.atsistemas.concesionario.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.concesionario.dto.VehiculoDTO;
import com.atsistemas.concesionario.entidades.Vehiculo;
import com.atsistemas.concesionario.persistencia.interfaces.VehiculoDao;
import com.atsistemas.concesionario.servicios.interfaces.OperacionesCRUD;
import com.atsistemas.concesionario.servicios.interfaces.VehiculoCRUD;

@Service
@Transactional
@Secured("ROLE_ADMINISTRATIVO")
public class ServicioVehiculo implements VehiculoCRUD{
	
	private  VehiculoDao vehiculoDao;
	
	@Autowired
	public ServicioVehiculo (VehiculoDao vehiculoDao){
		this.vehiculoDao = vehiculoDao;
	}
	
	public void alta(VehiculoDTO vehiculoDTO) {
		try {
			Mapper mapa = new DozerBeanMapper();
			Vehiculo vehiculoEntidad = mapa.map(vehiculoDTO, Vehiculo.class);
			vehiculoDao.save(vehiculoEntidad);
		} catch (Exception e) {
			System.out.println("ERROR al insertar vehiculo "+e.getMessage());
		}		
	}

	public void baja(long id) {
		try {
			vehiculoDao.delete(id);
		} catch (Exception e) {
			System.out.println("ERROR al borrar vehiculo "+e.getMessage());
		}
		
	}

	public void modificar(VehiculoDTO vehiculoDTO) {
		try {
			Mapper mapa = new DozerBeanMapper();
			Vehiculo vehiculoEntidad = mapa.map(vehiculoDTO, Vehiculo.class);
			vehiculoDao.save(vehiculoEntidad);
		} catch (Exception e) {
			System.out.println("ERROR al modificar vehiculo "+e.getMessage());
		}
		
	}

	public VehiculoDTO consultarPorId(long id) {
		VehiculoDTO vehiculoDTO = null;
		try {
			Mapper mapa = new DozerBeanMapper();
			Vehiculo vehiculoEntidad = vehiculoDao.getOne(id);
			vehiculoDTO = mapa.map(vehiculoEntidad, VehiculoDTO.class);
		} catch (Exception e) {
			System.out.println("ERROR al consultar vehiculo "+e.getMessage());
		}		
		return vehiculoDTO;
	}

	public List<VehiculoDTO> consultarTodos() {
		List <VehiculoDTO> listaVehiculoDTO = new ArrayList<VehiculoDTO>();
		try {
			Mapper mapa = new DozerBeanMapper();
			List<Vehiculo> listaVehiculoEntidad = vehiculoDao.findAll();
			for (Vehiculo vehiculoEntidad : listaVehiculoEntidad){
				VehiculoDTO vehiculoDTO= mapa.map(vehiculoEntidad, VehiculoDTO.class);
				listaVehiculoDTO.add(vehiculoDTO);
			}
		} catch (Exception e) {
			System.out.println("ERROR al consultar todos los vehiculos "+e.getMessage());
		}
		
		return listaVehiculoDTO;
	}

}
