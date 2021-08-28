package com.gmauricio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gmauricio.App;
import com.gmauricio.Controller.ApiController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@TestPropertySource(locations="classpath:application.properties")

public class ControllerTest {
	
	@Autowired
	private ApiController apiController;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
	}
	
	@Test
	public void testeSend() {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.post("/api/send")
					.contentType("application/json")
					.content("{'notification_type' : 'SUBSCRIPTION_RESTARTED', 'subscription': '5793cf6b3fd833521db8c420955e6f00'}")
					).andExpect(MockMvcResultMatchers.status().isOk());
							
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
