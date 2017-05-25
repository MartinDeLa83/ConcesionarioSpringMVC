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
import com.atsistemas.concesionario.servicios.interfaces.OperacionesCRUD;
import com.atsistemas.concesionario.servicios.interfaces.VehiculoCRUD;
import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.dto.VehiculoDTO;

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
public class RestVehiculoControlador {

	private VehiculoCRUD vehiculoCRUD;

	@Autowired
	public RestVehiculoControlador(VehiculoCRUD vehiculoCRUD) {
		super();
		this.vehiculoCRUD = vehiculoCRUD;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ClienteDTO> altaVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
		vehiculoCRUD.alta(vehiculoDTO);	
		return new ResponseEntity<ClienteDTO>(HttpStatus.CREATED);

	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<VehiculoDTO>> obtenerVehiculos() {
		System.out.println("Hola");
		List<VehiculoDTO> vehiculoDTO = vehiculoCRUD.consultarTodos();
		return new ResponseEntity<List<VehiculoDTO>>(vehiculoDTO, HttpStatus.OK);
	}
	
	

	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<VehiculoDTO> eliminarCliente(@PathVariable long id) {
		vehiculoCRUD.baja(id);
		return new ResponseEntity<VehiculoDTO>(HttpStatus.ACCEPTED);

	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<VehiculoDTO> modificarVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
		vehiculoCRUD.modificar(vehiculoDTO);
		return new ResponseEntity<VehiculoDTO>(HttpStatus.ACCEPTED);

	}

	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public ResponseEntity<VehiculoDTO> obtenerVehiculo(@PathVariable long id) {
		System.out.println("Consultando");
		VehiculoDTO vehiculoDTO = vehiculoCRUD.consultarPorId(id);
		return new ResponseEntity<VehiculoDTO>(vehiculoDTO, HttpStatus.OK);
	}

}
