package org.example.test.api;

import org.example.test.services.LocalizadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteAPI {

	public static final String SERVICE_URL = "CALCULADORASERVICE";

	@Autowired
	private LocalizadorServicio servicio;

	@RequestMapping(value = "/execute", method = RequestMethod.GET)
	public String execute() {
		servicio.setServiceURL(SERVICE_URL);
		return this.servicio.operacion("suma", new Operandos(4, 10));
	}
}
