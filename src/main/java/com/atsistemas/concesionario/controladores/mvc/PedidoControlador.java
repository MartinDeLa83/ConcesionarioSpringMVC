package com.atsistemas.concesionario.controladores.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atsistemas.concesionario.configuracion.objetosForm.PedidoForm;
import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.dto.ComercialDTO;
import com.atsistemas.concesionario.dto.PedidoDTO;
import com.atsistemas.concesionario.dto.VehiculoDTO;
import com.atsistemas.concesionario.servicios.interfaces.ClienteCRUD;
import com.atsistemas.concesionario.servicios.interfaces.ComercialCRUD;
import com.atsistemas.concesionario.servicios.interfaces.GestionPedidos;
import com.atsistemas.concesionario.servicios.interfaces.VehiculoCRUD;

@Controller
@RequestMapping(path="/Pedido")
public class PedidoControlador {
	
	private GestionPedidos gestionPedidos;
	private ClienteCRUD clienteCRUD;
	private VehiculoCRUD vehiculoCRUD;
	private ComercialCRUD comercialCRUD;

	@Autowired
	public PedidoControlador(GestionPedidos gestionPedidos,ClienteCRUD clienteCRUD,
			VehiculoCRUD vehiculoCRUD, ComercialCRUD comercialCRUD) {
		super();
		this.gestionPedidos = gestionPedidos;
		this.clienteCRUD = clienteCRUD;
		this.vehiculoCRUD = vehiculoCRUD;
		this.comercialCRUD = comercialCRUD;
	}
	
	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String mostrarFormularioAlta(Model model) {
		
		model.addAttribute("listaClientes", clienteCRUD.consultarTodos());
		model.addAttribute("listaVehiculos", vehiculoCRUD.consultarTodos());
		model.addAttribute("listaComerciales", comercialCRUD.consultarTodos());
		PedidoForm pedidoForm = new PedidoForm();
		System.out.println("creando pedidoForm");
		model.addAttribute("pedidoForm", pedidoForm);
		return "Pedidos/formulario";
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@ModelAttribute("pedidoForm") PedidoForm pedidoForm) {
		System.out.println("realizando alta");
			ClienteDTO clienteDTO= clienteCRUD.consultarPorId(pedidoForm.getIdCliente());
			ComercialDTO comercialDTO = comercialCRUD.consultarPorId(pedidoForm.getIdComercial());
			VehiculoDTO vehiculoDTO = vehiculoCRUD.consultarPorId(pedidoForm.getIdVehiculo());
			Date fechaHoy= new Date();
			gestionPedidos.lanzarPedido(clienteDTO, comercialDTO, vehiculoDTO, fechaHoy);
			return "redirect:/Pedido/lista";

	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET) 
	public String listar(Model model){		
		model.addAttribute("listaPedidos", gestionPedidos.consultarTodos());	
		return "Pedidos/lista";
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String listarEspecifico(@RequestParam(name="id", required=true) long id, Model model) {		
		List<PedidoDTO> resultado = new ArrayList<PedidoDTO>();		
		resultado.add(gestionPedidos.consultarPorId(id));		
		model.addAttribute("listaPedidos", resultado);		
		return "Pedidos/lista";
	}
	
	@RequestMapping(value = "/generarFactura/{id}", method = RequestMethod.GET)
	public String generarFactura(@PathVariable("id") Long id, Model model) {		
		gestionPedidos.recepcionarPedido(id);				
		return "redirect:/Pedido/lista";
	}
	
	/*
	@RequestMapping(value = "/modificacion/{id}", method = RequestMethod.GET)
	public String mostrarFormularioModificacion(@PathVariable("id") Long id, Model model) {		
		ClienteDTO clienteDTO = clienteCRUD.consultarPorId(id);
		model.addAttribute("clienteForm", clienteDTO);
		model.addAttribute("tipoOperacion", "modificacion");		
		return "Clientes/formulario";
	}
	
	@RequestMapping(value = "/borrado/{id}", method = RequestMethod.GET)
	public String borrar(@PathVariable("id") Long id, Model model) {		
		clienteCRUD.baja(id);		
		return "redirect:/Cliente/lista";
	}
	
	@RequestMapping(value = "/modificacion", method = RequestMethod.POST)
	public String modificar(@Valid @ModelAttribute("clienteForm") ClienteDTO clienteDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Clientes/formulario";
		} else {
			clienteCRUD.modificar(clienteDTO);
			return "redirect:/Cliente/lista";
		}
	}
*/
}
