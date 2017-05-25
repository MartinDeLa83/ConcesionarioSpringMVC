package com.atsistemas.concesionario.servicios.interfaces;

import java.util.List;

public interface OperacionesCRUD<E> {	
	void alta(E entidad);
	void baja(long id);
	void modificar(E entidad);
	E consultarPorId(long id);
	List<E> consultarTodos();
}
