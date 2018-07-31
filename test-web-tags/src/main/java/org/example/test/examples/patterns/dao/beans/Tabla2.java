package org.example.test.examples.patterns.dao.beans;

public class Tabla2 implements ITabla {
	private String campo1;

	private String campo2;

	private String campo3;

	public Tabla2(String campo1, String campo2, String campo3) {
		this.campo1 = campo1;
		this.campo2 = campo2;
		this.campo3 = campo3;
	}
	public boolean esTabla() {
		return false;
	}
	public String getCampo1() {
		return campo1;
	}
	public String getCampo2() {
		return campo2;
	}
	public String getCampo3() {
		return campo3;
	}
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

}
