package org.example.test.modelo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -788915881025648663L;

	private Long id;

	@NotNull(message = "el nombre es obligatorio")
	@Size(min = 2, max = 30, message = "Debe tener entre 2 y 30 caracteres")
	private String nombre;
	
	@Pattern(regexp="^\\+(?:[0-9] ?){6,14}[0-9]$", message="Debe comenzar con simbolo + y 11 digitos")
	private String telefono;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Long id, String nombre, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
