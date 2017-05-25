package com.atsistemas.concesionario.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.concesionario.dto.ComercialDTO;
import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.persistencia.interfaces.ComercialDao;
import com.atsistemas.concesionario.servicios.interfaces.ComercialCRUD;

@Service
@Transactional
@Secured("ROLE_GERENTE")
public class ServicioComercial implements ComercialCRUD{

	private  ComercialDao comercialDao;
	
	@Autowired
	public ServicioComercial (ComercialDao comercialDao){
		this.comercialDao = comercialDao;
	}
	
	public void alta(ComercialDTO comercialDTO) {
		try {
			Mapper mapa = new DozerBeanMapper();
			Comercial comercialEntidad = mapa.map(comercialDTO, Comercial.class);
			comercialDao.save(comercialEntidad);
		} catch (Exception e) {
			System.out.println("ERROR al insertar comercial "+e.getMessage());
		}
		
	}

	public void baja(long id) {
		try {
			comercialDao.delete(id);
		} catch (Exception e) {
			System.out.println("ERROR al borrar comercial "+e.getMessage());
		}		
	}

	public void modificar(ComercialDTO comercialDTO) {
		try {
			Mapper mapa = new DozerBeanMapper();
			Comercial comercialEntidad = mapa.map(comercialDTO, Comercial.class);
			comercialDao.save(comercialEntidad);
		} catch (Exception e) {
			System.out.println("ERROR al insertar comercial "+e.getMessage());
		}	
		
	}

	public ComercialDTO consultarPorId(long id) {
		ComercialDTO comercialDTO = null;
		try {
			Mapper mapa = new DozerBeanMapper();
			Comercial comercialEntidad = comercialDao.getOne(id);
			comercialDTO = mapa.map(comercialEntidad, ComercialDTO.class);
		} catch (Exception e) {
			System.out.println("ERROR al consultar comercial "+e.getMessage());
		}		
		return comercialDTO;
	}

	public List<ComercialDTO> consultarTodos() {
		List <ComercialDTO> listaComercialDTO = new ArrayList<ComercialDTO>();
		try {
			Mapper mapa = new DozerBeanMapper();
			List<Comercial> listaComercialEntidad = comercialDao.findAll();
			for (Comercial comercialEntidad : listaComercialEntidad){
				ComercialDTO comercialDTO= mapa.map(comercialEntidad, ComercialDTO.class);
				listaComercialDTO.add(comercialDTO);
			}
		} catch (Exception e) {
			System.out.println("ERROR al consultar todos los comerciales "+e.getMessage());
		}
		
		return listaComercialDTO;
	}



}
