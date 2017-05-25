package com.atsistemas.concesionario.controladores.mvc;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.dto.ComercialDTO;
import com.atsistemas.concesionario.dto.FacturaDTO;
import com.atsistemas.concesionario.dto.PedidoDTO;
import com.atsistemas.concesionario.dto.VehiculoDTO;
import com.atsistemas.concesionario.servicios.interfaces.ClienteCRUD;
import com.atsistemas.concesionario.servicios.interfaces.ComercialCRUD;
import com.atsistemas.concesionario.servicios.interfaces.GestionFacturas;
import com.atsistemas.concesionario.servicios.interfaces.GestionPedidos;
import com.atsistemas.concesionario.servicios.interfaces.VehiculoCRUD;

@Controller
@RequestMapping(path="/Factura")
public class FacturaControlador {
	
	private GestionPedidos gestionPedidos;
	private ClienteCRUD clienteCRUD;
	private VehiculoCRUD vehiculoCRUD;
	private ComercialCRUD comercialCRUD;
	private GestionFacturas gestionFacturas;

	@Autowired
	public FacturaControlador(GestionPedidos gestionPedidos,ClienteCRUD clienteCRUD,
			VehiculoCRUD vehiculoCRUD, ComercialCRUD comercialCRUD, GestionFacturas gestionFacturas) {
		super();
		this.gestionPedidos = gestionPedidos;
		this.clienteCRUD = clienteCRUD;
		this.vehiculoCRUD = vehiculoCRUD;
		this.comercialCRUD = comercialCRUD;
		this.gestionFacturas = gestionFacturas;
	}
	

	
	@RequestMapping(value = "/lista", method = RequestMethod.GET) 
	public String listar(Model model){		
		model.addAttribute("listaFacturas", gestionFacturas.consultarTodos());	
		return "Facturas/lista";
	}
	
	@RequestMapping(value = "/detalle/{id}", method = RequestMethod.GET)
	public String mostrarDetalleFactura(@PathVariable("id") Long id,Model model) {	
		
		FacturaDTO facturaDTO = gestionFacturas.consultarPorId(id);
		PedidoDTO pedidoDTO = facturaDTO.getPedido();
		VehiculoDTO vehiculoDTO = pedidoDTO.getVehiculo();
		ClienteDTO clienteDTO = pedidoDTO.getCliente();
		ComercialDTO comercialDTO = pedidoDTO.getComercial();
		
		model.addAttribute("nombreCliente", clienteDTO.getNombre());
		model.addAttribute("nombreComercial", comercialDTO.getNombre());
		
		model.addAttribute("modelo", vehiculoDTO.getModelo());
		model.addAttribute("color", vehiculoDTO.getColor());
		model.addAttribute("motor", vehiculoDTO.getMotor());
		model.addAttribute("descripcion", vehiculoDTO.getDescripcion());
		
		model.addAttribute("idFactura", facturaDTO.getId());
		model.addAttribute("estadoFactura", facturaDTO.getEstado());
		model.addAttribute("total", facturaDTO.getTotal());
		
		model.addAttribute("numPedido", pedidoDTO.getId());
				
		return "Facturas/detalle";
	}
	
	@RequestMapping(value = "/cobrar/{id}", method = RequestMethod.GET)
	public String cobrar(@PathVariable("id") Long id,Model model) {	
		
		gestionFacturas.cobrarFactura(id);;
				
		return "redirect:/Factura/lista";
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String listarEspecifico(@RequestParam(name="id", required=true) long id, Model model) {		
		List<FacturaDTO> resultado = new ArrayList<FacturaDTO>();		
		resultado.add(gestionFacturas.consultarPorId(id));		
		model.addAttribute("listaFacturas", resultado);		
		return "Facturas/lista";
	}
	
}
