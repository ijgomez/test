package org.example.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.example.test.CoreConfiguration;
import org.example.test.domain.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreConfiguration.class)
public class IndexServiceTest {

	@Autowired
	private IndexService indexService;

	@Test
	public void testPropertiesLoad() {
		assertNotNull(indexService);
		
		assertThat(indexService.getRepository()).isEqualTo("core_test_resources");
	}

	@Test
	public void testFindAll() {
		List<Index> data = indexService.findAll();

		assertNotNull(data);
		assertThat(data).isNotNull();
	}

}
