package org.example.test.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.example.test.configuration.IndexServiceConfiguration;
import org.example.test.domain.Index;
import org.example.test.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(IndexServiceConfiguration.class)
public class IndexService {

	@Autowired
	private IndexRepository indexRepository;
	
	@Autowired
	private IndexServiceConfiguration configuration;
	
	public String getRepository() {
		return this.configuration.getRepository();
	}
	
	public List<Index> findAll(){
		return StreamSupport.stream(this.indexRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
