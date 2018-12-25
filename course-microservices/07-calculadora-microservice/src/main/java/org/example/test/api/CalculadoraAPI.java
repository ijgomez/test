package org.example.test.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraAPI {

	@RequestMapping(value = "/calculadora/suma", method = RequestMethod.POST)
	public double suma(@RequestBody Operandos operandos) {
		return operandos.getN1() + operandos.getN2();
	}
	
	@RequestMapping(value = "/calculadora/resta", method = RequestMethod.POST)
	public double resta(@RequestBody Operandos operandos) {
		return operandos.getN1() - operandos.getN2();
	}
	
	@RequestMapping(value = "/calculadora/multi", method = RequestMethod.POST)
	public double multiplicacion(@RequestBody Operandos operandos) {
		return operandos.getN1() * operandos.getN2();
	}
	
	@RequestMapping(value = "/calculadora/div", method = RequestMethod.POST)
	public double division(@RequestBody Operandos operandos) {
		return operandos.getN1() / operandos.getN2();
	}
}
