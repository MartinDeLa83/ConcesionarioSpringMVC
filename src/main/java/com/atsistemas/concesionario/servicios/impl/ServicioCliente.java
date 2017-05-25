package com.atsistemas.concesionario.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.entidades.Cliente;
import com.atsistemas.concesionario.persistencia.interfaces.ClienteDao;
import com.atsistemas.concesionario.servicios.interfaces.ClienteCRUD;

@Service
@Transactional
@Secured("ROLE_COMERCIAL")
public class ServicioCliente implements ClienteCRUD  {
	
	private  ClienteDao clienteDao;
	
	@Autowired
	public ServicioCliente (ClienteDao clienteDao){
		this.clienteDao = clienteDao;
	}
	
	public void alta(ClienteDTO clienteDTO) {
		try {
			Mapper mapa = new DozerBeanMapper();
			Cliente clienteEntidad = mapa.map(clienteDTO, Cliente.class);
			clienteDao.save(clienteEntidad);
		} catch (Exception e) {
			System.out.println("ERROR al insertar cliente "+e.getMessage());
		}
		
	}
	
	public void modificar(ClienteDTO clienteDTO) {
		try {
			Mapper mapa = new DozerBeanMapper();
			Cliente clienteEntidad = mapa.map(clienteDTO, Cliente.class);
			clienteDao.save(clienteEntidad);
		} catch (Exception e) {
			System.out.println("ERROR al insertar cliente "+e.getMessage());
		}		
	}

	public void baja(long id) {
		try {
			Cliente cliente = clienteDao.getOne(id);
			cliente.getPedidos();
			clienteDao.delete(cliente);
		} catch (Exception e) {
			System.out.println("ERROR al borrar cliente "+e.getMessage());
		}		
	}

	public ClienteDTO consultarPorId(long id) {
		ClienteDTO clienteDTO = null;
		try {
			Mapper mapa = new DozerBeanMapper();
			Cliente clienteEntidad = clienteDao.getOne(id);
			clienteDTO = mapa.map(clienteEntidad, ClienteDTO.class);
		} catch (Exception e) {
			System.out.println("ERROR al consultar cliente "+e.getMessage());
		}
		
		return clienteDTO;
	}

	public List<ClienteDTO> consultarTodos() {
		List <ClienteDTO> listaClienteDTO = new ArrayList<ClienteDTO>();
		try {
			Mapper mapa = new DozerBeanMapper();
			List<Cliente> listaClienteEntidad = clienteDao.findAll();
			for (Cliente clienteEntidad : listaClienteEntidad){
				ClienteDTO clienteDTO= mapa.map(clienteEntidad, ClienteDTO.class);
				listaClienteDTO.add(clienteDTO);
			}
		} catch (Exception e) {
			System.out.println("ERROR al consultar todos los clientes "+e.getMessage());
		}
		
		return listaClienteDTO;
	}

}
