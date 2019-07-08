package org.example.test.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfiguration {

	@Bean
	public DataSource getDataSource() {
		
		DriverManagerDataSource bds = new DriverManagerDataSource();
		bds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		bds.setUrl("jdbc:hsqldb:hsql://localhost/taskmanager");
		bds.setUsername("SA");
		bds.setPassword("");
		
		return bds;
	}
}
