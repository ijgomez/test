package org.example.test;

import java.util.Date;

@ExportacionCSV
public class Registro {

	@ExportacionColumn(name="id")
	private long id;
	
	@ExportacionColumn(name="nombre")
	private String nombre;
	
	@ExportacionColumn(name="Fecha de Registro en Base de Datos")
	private Date fxRegistroBD;
	
	public Registro() {
		super();
	}

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

	public Date getFxRegistroBD() {
		return fxRegistroBD;
	}

	public void setFxRegistroBD(Date fxRegistroBD) {
		this.fxRegistroBD = fxRegistroBD;
	}
	
	
}
