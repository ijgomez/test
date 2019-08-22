package org.example.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.test.services.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class ApplicationContextTest {

	@Autowired
    private IndexService indexService;

    @Test
    public void contextLoads() {
        assertThat(indexService).isNotNull();
        assertThat(indexService.getRepository()).isEqualTo("swing_test_resources");
    }

}
