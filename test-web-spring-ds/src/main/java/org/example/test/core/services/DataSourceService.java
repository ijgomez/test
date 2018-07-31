package org.example.test.core.services;

import java.util.ArrayList;
import java.util.List;

import org.example.test.core.domain.RespuestaDS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataSourceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceService.class);
	
//	private DataSource dataSource;

	public List<RespuestaDS> execute(List<String> sqls) {
		List<RespuestaDS> results;
		
		LOGGER.info("Consultas ({}) a testear:", sqls.size());
		results = new ArrayList<RespuestaDS>();
		for (String sql : sqls) {
			RespuestaDS respuestaDS;
			
			respuestaDS = new RespuestaDS();
			respuestaDS.setSql(sql);
			//TODO
			
			LOGGER.info("sql: {}", sql);
			
			results.add(respuestaDS);
			
		}
		
		return results;
	}

}
