package org.example.test.api;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.example.test.dao.Cliente;
import org.example.test.dao.ClientesRepository;
import org.example.test.modelo.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientesAPI {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private Mapper mapper;
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public Cliente buscar(@PathVariable("id") Long id) {
		return this.clientesRepository.findById(id).get();
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ClienteDTO alta(@RequestBody @Valid ClienteDTO clienteDTO) {
		Cliente cliente = this.mapper.map(clienteDTO, Cliente.class);
		cliente = clientesRepository.saveAndFlush(cliente);
		return this.mapper.map(cliente, ClienteDTO.class);
	}
		
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public String eliminar(@PathVariable("id") Long id) {
		clientesRepository.deleteById(id);
		return "Cliente eliminado";
	}
	
	
	@RequestMapping(value = "/cliente", method = RequestMethod.PUT)
	public Cliente modificar(@RequestBody Cliente actualizado) {
		return this.clientesRepository.saveAndFlush(actualizado);
	}
}
