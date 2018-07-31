package org.example.test.examples.historicos;

public class HistoricosException extends Exception {

	private static final long serialVersionUID = 2181527374155013577L;
	
	private String codigo;
	
	private String mensaje;
	
	public HistoricosException(String codigo, String mensaje){
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


}
