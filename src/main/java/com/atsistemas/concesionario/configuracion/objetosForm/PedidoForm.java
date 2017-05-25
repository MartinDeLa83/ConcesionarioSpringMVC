package com.atsistemas.concesionario.configuracion.objetosForm;

public class PedidoForm {
	
	long idPedido;
	long idCliente;
	long idVehiculo;
	long idComercial;
	
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public long getIdComercial() {
		return idComercial;
	}
	public void setIdComercial(long idComercial) {
		this.idComercial = idComercial;
	}
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPedido ^ (idPedido >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoForm other = (PedidoForm) obj;
		if (idPedido != other.idPedido)
			return false;
		return true;
	}
	
	
	
	
	


}
