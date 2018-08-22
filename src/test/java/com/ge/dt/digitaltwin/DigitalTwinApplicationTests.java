package com.ge.dt.digitaltwin;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitalTwinApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		System.out.println("******** Junit Mock Setup *************");

	}

	@Test
	public void customerTable() throws Exception {
		assertThat(2 + 1, is(equalTo(3)));
		mockMvc.perform(MockMvcRequestBuilders.get("/modality?customerName=CHANGGENG%20MEMORIAL%20HOSPITAL(KAOHSIUNG)")
				.accept(MediaType.APPLICATION_JSON)).andDo(print());
	}

	@Test
	public void serviceTable() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/modality?age=P5&region=EGM&sysCovLevWarrantyc=Contract")
				.accept(MediaType.APPLICATION_JSON)).andDo(print());
	}

}
