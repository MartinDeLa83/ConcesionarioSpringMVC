package com.atsistemas.concesionario.servicios.interfaces;

import java.util.Date;

import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.dto.ComercialDTO;
import com.atsistemas.concesionario.dto.FacturaDTO;
import com.atsistemas.concesionario.dto.PedidoDTO;
import com.atsistemas.concesionario.dto.VehiculoDTO;

public interface GestionPedidos extends OperacionesCRUD<PedidoDTO> {
	
	PedidoDTO lanzarPedido(ClienteDTO cliente, ComercialDTO comercial, VehiculoDTO vehiculo, Date fecha);
	String consultarEstadoPedido(long idPedido);
	FacturaDTO recepcionarPedido(long idPedido);
	
	
}
