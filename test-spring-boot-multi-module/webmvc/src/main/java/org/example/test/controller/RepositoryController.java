package org.example.test.controller;

import org.example.test.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepositoryController {
	
	@Autowired
	private IndexService indexService;
	
	@GetMapping("/repo")
	public String getRepository() {
		return indexService.getRepository();
	}

}
