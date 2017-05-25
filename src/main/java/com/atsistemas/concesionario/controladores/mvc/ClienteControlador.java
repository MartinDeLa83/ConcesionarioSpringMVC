package com.atsistemas.concesionario.controladores.mvc;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.servicios.interfaces.ClienteCRUD;

@Controller
@RequestMapping(path="/Cliente")
public class ClienteControlador {

	private ClienteCRUD clienteCRUD;

	@Autowired
	public ClienteControlador(ClienteCRUD clienteCRUD) {
		super();
		this.clienteCRUD = clienteCRUD;
	}
	
	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String mostrarFormularioAlta(Model model) {	
		ClienteDTO clienteDTO = new ClienteDTO();
		model.addAttribute("clienteForm", clienteDTO);		
		return "Clientes/formulario";
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@Valid @ModelAttribute("clienteForm") ClienteDTO clienteDTO, BindingResult result) {
		System.out.println("realizando alta");
		if (result.hasErrors()) {
			return "Clientes/formulario";
		} else {		
			clienteCRUD.alta(clienteDTO);
			return "redirect:/Cliente/lista";
		}

	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET) 
	public String listar(Model model){		
		model.addAttribute("listaClientes", clienteCRUD.consultarTodos());	
		return "Clientes/lista";
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String listarEspecifico(@RequestParam(name="id", required=true) long id, Model model) {		
		List<ClienteDTO> resultado = new ArrayList<ClienteDTO>();		
		resultado.add(clienteCRUD.consultarPorId(id));		
		model.addAttribute("listaClientes", resultado);		
		return "Clientes/lista";
	}
	
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

}
