package org.example.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.example.test.ApplicationWebMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ApplicationWebMvcConfig.class)
//@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationWebMvcConfig.class)
@WebAppConfiguration
public class RepositoryControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
	private RepositoryController controller;
    
    @Before
	public void setup(){
		// Process mock annotations
        MockitoAnnotations.initMocks(this);
        
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
   
	@Test
	public void testGetRepository() throws Exception {

		   // when + then
	        this.mockMvc.perform(get("/repo"))
	                .andExpect(status().isOk())
	                .andExpect(content().string("webmvc_test_resources"));
	}

}
