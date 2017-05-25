package com.atsistemas.concesionario.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.atsistemas.concesionario.dto.FacturaDTO;
import com.atsistemas.concesionario.dto.PedidoDTO;
import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.persistencia.interfaces.FacturaDao;
import com.atsistemas.concesionario.servicios.interfaces.GestionFacturas;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Secured("ROLE_ADMINISTRATIVO")
public class ServicioGestionFacturas implements GestionFacturas {
	
	private FacturaDao facturaDao;
	
	@Autowired
	public ServicioGestionFacturas (FacturaDao facturaDao){
		this.facturaDao = facturaDao;
	}

	public void cobrarFactura(long idFactura) {
		try {
			Factura factura = facturaDao.getOne(idFactura);		
			//Pendiente de cambio
			factura.setEstado("cobrada");
			facturaDao.save(factura);
		} catch (Exception e) {
			System.out.println("ERROR al cobrar factura "+e.getMessage());
		}
	}

	@Override
	public void alta(FacturaDTO entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void baja(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(FacturaDTO entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FacturaDTO consultarPorId(long id) {
		FacturaDTO facturaDTO = null;
		try {
			Mapper mapa = new DozerBeanMapper();
			Factura facturaEntidad = facturaDao.getOne(id);
			facturaDTO = mapa.map(facturaEntidad, FacturaDTO.class);
		} catch (Exception e) {
			System.out.println("ERROR al consultar pedido "+e.getMessage());
		}
		
		return facturaDTO;
	}

	@Override
	public List<FacturaDTO> consultarTodos() {
		List <FacturaDTO> listaFacturaDTO = new ArrayList<FacturaDTO>();
		try {
			Mapper mapa = new DozerBeanMapper();
			List<Factura> listaFacturaEntidad = facturaDao.findAll();
			for (Factura facturaEntidad : listaFacturaEntidad){
				FacturaDTO facturaDTO= mapa.map(facturaEntidad, FacturaDTO.class);
				listaFacturaDTO.add(facturaDTO);
			}
		} catch (Exception e) {
			System.out.println("ERROR al consultar todos los pedidos "+e.getMessage());
		}
		
		return listaFacturaDTO;
	}

}
