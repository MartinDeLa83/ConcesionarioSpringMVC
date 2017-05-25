package com.atsistemas.concesionario.servicios.interfaces;

import com.atsistemas.concesionario.dto.FacturaDTO;

public interface GestionFacturas extends OperacionesCRUD<FacturaDTO>{
	
	void cobrarFactura(long idFactura);
}
