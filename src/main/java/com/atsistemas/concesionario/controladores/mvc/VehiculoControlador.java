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

import com.atsistemas.concesionario.dto.VehiculoDTO;
import com.atsistemas.concesionario.servicios.interfaces.VehiculoCRUD;

@Controller
@RequestMapping(path="/Vehiculo")
public class VehiculoControlador {

	private VehiculoCRUD vehiculoCRUD;

	@Autowired
	public VehiculoControlador(VehiculoCRUD vehiculoCRUD) {
		super();
		this.vehiculoCRUD = vehiculoCRUD;
	}
	
	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String mostrarFormularioAlta(Model model) {	
		VehiculoDTO vehiculoDTO = new VehiculoDTO();
		model.addAttribute("vehiculoForm", vehiculoDTO);		
		return "Vehiculos/formulario";
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String alta(@Valid @ModelAttribute("vehiculoForm") VehiculoDTO vehiculoDTO, BindingResult result) {
		System.out.println("realizando alta");
		if (result.hasErrors()) {
			return "Vehiculos/formulario";
		} else {
			System.out.println("Crud");
			vehiculoCRUD.alta(vehiculoDTO);
			return "redirect:/Vehiculo/lista";
		}

	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET) 
	public String listar(Model model){		
		model.addAttribute("listaVehiculos", vehiculoCRUD.consultarTodos());	
		return "Vehiculos/lista";
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String listarEspecifico(@RequestParam(name="id", required=true) long id, Model model) {		
		List<VehiculoDTO> resultado = new ArrayList<VehiculoDTO>();		
		resultado.add(vehiculoCRUD.consultarPorId(id));		
		model.addAttribute("listaVehiculos", resultado);		
		return "Vehiculos/lista";
	}
	
	@RequestMapping(value = "/modificacion/{id}", method = RequestMethod.GET)
	public String mostrarFormularioModificacion(@PathVariable("id") Long id, Model model) {		
		VehiculoDTO vehiculoDTO = vehiculoCRUD.consultarPorId(id);
		model.addAttribute("vehiculoForm", vehiculoDTO);
		model.addAttribute("tipoOperacion", "modificacion");		
		return "Vehiculos/formulario";
	}
	
	@RequestMapping(value = "/borrado/{id}", method = RequestMethod.GET)
	public String borrar(@PathVariable("id") Long id, Model model) {		
		vehiculoCRUD.baja(id);		
		return "redirect:/Vehiculo/lista";
	}
	
	@RequestMapping(value = "/modificacion", method = RequestMethod.POST)
	public String modificar(@Valid @ModelAttribute("vehiculoForm") VehiculoDTO vehiculoDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Vehiculos/formulario";
		} else {
			vehiculoCRUD.modificar(vehiculoDTO);
			return "redirect:/Vehiculo/lista";
		}
	}

}
