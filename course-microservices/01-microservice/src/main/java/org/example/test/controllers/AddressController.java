package org.example.test.controllers;

import org.example.test.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public String getAddress() throws Exception {
		return this.locationService.getAddress();
	}
}
