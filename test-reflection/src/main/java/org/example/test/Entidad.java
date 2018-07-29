package org.example.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entidad implements Serializable {

	private static final long serialVersionUID = 2356759505516798692L;

	private String atributo1;
	
	private String atributo2;
	
	private String atributo3;
	
	private String atributo4;
	
	private String atributo5;
	
	private List<Entidad> atributos = new ArrayList<Entidad>();
	
	public Entidad() {
		super();
	}

	public String getAtributo1() {
		return atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	public String getAtributo2() {
		return atributo2;
	}

	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}

	public String getAtributo3() {
		return atributo3;
	}

	public void setAtributo3(String atributo3) {
		this.atributo3 = atributo3;
	}

	public String getAtributo4() {
		return atributo4;
	}

	public void setAtributo4(String atributo4) {
		this.atributo4 = atributo4;
	}

	public String getAtributo5() {
		return atributo5;
	}

	public void setAtributo5(String atributo5) {
		this.atributo5 = atributo5;
	}
	
	public List<Entidad> getAtributos() {
		return atributos;
	}
	
	public void setAtributos(List<Entidad> atributos) {
		this.atributos = atributos;
	}
}
