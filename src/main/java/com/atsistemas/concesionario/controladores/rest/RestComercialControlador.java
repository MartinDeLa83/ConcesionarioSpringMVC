package com.atsistemas.concesionario.controladores.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.dto.ComercialDTO;
import com.atsistemas.concesionario.servicios.interfaces.ComercialCRUD;

@RestController
@RequestMapping(
		path="/Vehiculo", 
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
public class RestComercialControlador {
	
	private ComercialCRUD comercialCRUD;

	@Autowired
	public RestComercialControlador(ComercialCRUD comercialCRUD) {
		super();
		this.comercialCRUD = comercialCRUD;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ComercialDTO> altaComercial(@RequestBody ComercialDTO comercialDTO) {
		comercialCRUD.alta(comercialDTO);	
		return new ResponseEntity<ComercialDTO>(HttpStatus.CREATED);

	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ComercialDTO>> obtenerComerciales() {
		List<ComercialDTO> comercialesDTO = comercialCRUD.consultarTodos();
		return new ResponseEntity<List<ComercialDTO>>(comercialesDTO, HttpStatus.OK);
	}
	
	

	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ComercialDTO> eliminarComercial(@PathVariable long id) {
		comercialCRUD.baja(id);
		return new ResponseEntity<ComercialDTO>(HttpStatus.ACCEPTED);

	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ComercialDTO> modificarComercial(@RequestBody ComercialDTO comercialDTO) {
		comercialCRUD.modificar(comercialDTO);
		return new ResponseEntity<ComercialDTO>(HttpStatus.ACCEPTED);

	}

	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ComercialDTO> obtenerComercial(@PathVariable long id) {
		ComercialDTO comercialDTO = comercialCRUD.consultarPorId(id);
		return new ResponseEntity<ComercialDTO>(comercialDTO, HttpStatus.OK);
	}

}
