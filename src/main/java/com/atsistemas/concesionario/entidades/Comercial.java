package com.atsistemas.concesionario.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COMERCIAL")
public class Comercial {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String nombre;
	private String correo;
	private String telefono;
		
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		      name="COMERCIAL_CLIENTE",
		      joinColumns=@JoinColumn(name="COMERCIAL_ID", referencedColumnName="ID"),
		      inverseJoinColumns=@JoinColumn(name="CLIENTE_ID", referencedColumnName="ID"))
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy="comercial")
	private List<Pedido> pedidos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Comercial other = (Comercial) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comercial [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono
				+ ", clientes=" + clientes + ", pedidos=" + pedidos + "]";
	}

	
}
