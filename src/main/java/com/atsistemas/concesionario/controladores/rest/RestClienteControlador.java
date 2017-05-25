package com.atsistemas.concesionario.controladores.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.atsistemas.concesionario.servicios.interfaces.ClienteCRUD;
import com.atsistemas.concesionario.dto.ClienteDTO;

@RestController
@RequestMapping(
		path="/Cliente", 
		//La respuesta, el @ResponseBody
		produces= {
				MediaType.APPLICATION_JSON_VALUE, 
				MediaType.APPLICATION_XML_VALUE
		},
		consumes={
				MediaType.APPLICATION_JSON_VALUE, 
				MediaType.APPLICATION_XML_VALUE
		}
)
public class RestClienteControlador {

	private ClienteCRUD clienteCRUD;

	@Autowired
	public RestClienteControlador(ClienteCRUD clienteCRUD) {
		super();
		this.clienteCRUD = clienteCRUD;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ClienteDTO> altaCliente(@RequestBody ClienteDTO clienteDTO) {
		clienteCRUD.alta(clienteDTO);	
		return new ResponseEntity<ClienteDTO>(HttpStatus.CREATED);

	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
		System.out.println("Hola");
		List<ClienteDTO> clientesDTO = clienteCRUD.consultarTodos();
		return new ResponseEntity<List<ClienteDTO>>(clientesDTO, HttpStatus.OK);
	}
	
	

	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ClienteDTO> eliminarCliente(@PathVariable long id) {
		clienteCRUD.baja(id);
		return new ResponseEntity<ClienteDTO>(HttpStatus.ACCEPTED);

	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ClienteDTO> modificarCliente(@RequestBody ClienteDTO clienteDTO) {
		clienteCRUD.modificar(clienteDTO);
		return new ResponseEntity<ClienteDTO>(HttpStatus.ACCEPTED);

	}

	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable long id) {
		System.out.println("Consultando");
		ClienteDTO clienteDTO = (ClienteDTO) clienteCRUD.consultarPorId(id);
		return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
	}


	


}
