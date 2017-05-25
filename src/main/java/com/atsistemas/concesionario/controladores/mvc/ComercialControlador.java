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

import com.atsistemas.concesionario.dto.ComercialDTO;
import com.atsistemas.concesionario.servicios.interfaces.ComercialCRUD;

@Controller
@RequestMapping(path="/Comercial")
public class ComercialControlador {

	private ComercialCRUD comercialCRUD;

	@Autowired
	public ComercialControlador(ComercialCRUD comercialCRUD) {
		super();
		this.comercialCRUD = comercialCRUD;
	}
	
	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String mostrarFormularioAlta(Model model) {	
		ComercialDTO comercialDTO = new ComercialDTO();
		model.addAttribute("comercialForm", comercialDTO);		
		return "Comerciales/formulario";
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@Valid @ModelAttribute("comercialForm") ComercialDTO comercialDTO, BindingResult result) {
		System.out.println("realizando alta");
		if (result.hasErrors()) {
			return "Comerciales/formulario";
		} else {		
			comercialCRUD.alta(comercialDTO);
			return "redirect:/Comercial/lista";
		}

	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET) 
	public String listar(Model model){		
		model.addAttribute("listaComerciales", comercialCRUD.consultarTodos());	
		return "Comerciales/lista";
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String listarEspecifico(@RequestParam(name="id", required=true) long id, Model model) {		
		List<ComercialDTO> resultado = new ArrayList<ComercialDTO>();		
		resultado.add(comercialCRUD.consultarPorId(id));		
		model.addAttribute("listaComerciales", resultado);		
		return "Comerciales/lista";
	}
	
	@RequestMapping(value = "/modificacion/{id}", method = RequestMethod.GET)
	public String mostrarFormularioModificacion(@PathVariable("id") Long id, Model model) {		
		ComercialDTO comercialDTO = comercialCRUD.consultarPorId(id);
		model.addAttribute("comercialForm", comercialDTO);
		model.addAttribute("tipoOperacion", "modificacion");		
		return "Comerciales/formulario";
	}
	
	@RequestMapping(value = "/borrado/{id}", method = RequestMethod.GET)
	public String borrar(@PathVariable("id") Long id, Model model) {		
		comercialCRUD.baja(id);		
		return "redirect:/Comercial/lista";
	}
	
	@RequestMapping(value = "/modificacion", method = RequestMethod.POST)
	public String modificar(@Valid @ModelAttribute("comercialForm") ComercialDTO comercialDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Comerciales/formulario";
		} else {
			comercialCRUD.modificar(comercialDTO);
			return "redirect:/Comercial/lista";
		}
	}

}
