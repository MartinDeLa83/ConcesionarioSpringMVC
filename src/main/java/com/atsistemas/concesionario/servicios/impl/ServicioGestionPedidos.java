package com.atsistemas.concesionario.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.concesionario.dto.ClienteDTO;
import com.atsistemas.concesionario.dto.ComercialDTO;
import com.atsistemas.concesionario.dto.FacturaDTO;
import com.atsistemas.concesionario.dto.PedidoDTO;
import com.atsistemas.concesionario.dto.VehiculoDTO;
import com.atsistemas.concesionario.entidades.Cliente;
import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.entidades.Vehiculo;
import com.atsistemas.concesionario.persistencia.interfaces.FacturaDao;
import com.atsistemas.concesionario.persistencia.interfaces.PedidoDao;
import com.atsistemas.concesionario.servicios.interfaces.GestionPedidos;

@Service
@Transactional
@Secured("ROLE_COMERCIAL")
public class ServicioGestionPedidos implements GestionPedidos {
	
	private  PedidoDao pedidoDao;
	private  FacturaDao facturaDao;
	
	@Autowired
	public ServicioGestionPedidos (PedidoDao pedidoDao, FacturaDao facturaDao ){
		this.pedidoDao = pedidoDao;
		this.facturaDao = facturaDao;
	}
	

	public String consultarEstadoPedido(long idPedido) {
		String estado="";		
		try {
			estado = pedidoDao.findOne(idPedido).getEstado();
		} catch (Exception e) {
			System.out.println("ERROR al consultar estado del pedido "+e.getMessage());
		}
		
		return estado;
	}

	public FacturaDTO recepcionarPedido(long idPedido) {
		
		FacturaDTO facturaDTO = null;
		
		try {
			//Obtenemos el pedido
			Pedido pedido = pedidoDao.findOne(idPedido);
			pedido.setEstado("entregado");
			
			//Creamos la factura
			Factura factura = new Factura();
			factura.setPedido(pedido);
			factura.setEstado("no cobrada");
			factura.setFecha(new Date());
			long precioVehiculo=Long.parseLong(pedido.getVehiculo().getPrecio());
			factura.setTotal(precioVehiculo);
			
			Mapper mapa = new DozerBeanMapper();
			facturaDTO = mapa.map(factura, FacturaDTO.class);
			
			
			facturaDao.save(factura);
			
			//Guardamos el pedido
			//pedido.setFactura(factura);
			//pedidoDao.save(pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR al recepcionar pedido "+e.getMessage());
		}
		
		
		
		return facturaDTO;
	}


	public PedidoDTO lanzarPedido(ClienteDTO clienteDTO, ComercialDTO comercialDTO, VehiculoDTO vehiculoDTO, Date fecha) {
		PedidoDTO pedidoDTO = null;
		
		try {
			//Mapeamos con dtos con entidades
			Mapper mapa = new DozerBeanMapper();
			Cliente clienteEntidad = mapa.map(clienteDTO, Cliente.class);
			Comercial comercialEntidad = mapa.map(comercialDTO, Comercial.class);
			Vehiculo vehiculoEntidad = mapa.map(vehiculoDTO, Vehiculo.class);
			
			//Creamos pedido
			Pedido pedido = new Pedido();
			pedido.setCliente(clienteEntidad);
			pedido.setComercial(comercialEntidad);
			pedido.setVehiculo(vehiculoEntidad);
			pedido.setFecha(fecha);
			pedido.setEstado("en proceso");
			
			pedidoDTO = mapa.map(pedido, PedidoDTO.class);
			
			//Persistimos pedido en la bbdd
			pedidoDao.save(pedido);
			
			

		} catch (Exception e) {
			System.out.println("ERROR al lanzar pedido "+e.getMessage());
		}
		
		return pedidoDTO;
	}


	@Override
	public void alta(PedidoDTO entidad) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void baja(long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void modificar(PedidoDTO entidad) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public PedidoDTO consultarPorId(long id) {
		PedidoDTO pedidoDTO = null;
		try {
			Mapper mapa = new DozerBeanMapper();
			Pedido pedidoEntidad = pedidoDao.getOne(id);
			pedidoDTO = mapa.map(pedidoEntidad, PedidoDTO.class);
		} catch (Exception e) {
			System.out.println("ERROR al consultar pedido "+e.getMessage());
		}
		
		return pedidoDTO;
	}


	@Override
	public List<PedidoDTO> consultarTodos() {
		List <PedidoDTO> listaPedidoDTO = new ArrayList<PedidoDTO>();
		try {
			Mapper mapa = new DozerBeanMapper();
			List<Pedido> listaPedidoEntidad = pedidoDao.findAll();
			for (Pedido pedidoEntidad : listaPedidoEntidad){
				PedidoDTO pedidoDTO= mapa.map(pedidoEntidad, PedidoDTO.class);
				listaPedidoDTO.add(pedidoDTO);
			}
		} catch (Exception e) {
			System.out.println("ERROR al consultar todos los pedidos "+e.getMessage());
		}
		
		return listaPedidoDTO;
	}

}
