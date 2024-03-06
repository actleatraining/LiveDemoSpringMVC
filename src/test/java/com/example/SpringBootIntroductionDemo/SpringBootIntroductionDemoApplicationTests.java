package com.example.SpringBootIntroductionDemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootIntroductionDemoApplicationTests {
	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void testGetDog() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.get("/dog")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Pluto")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("Fido")));
	}

	@Test
	public void testGetDogById() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.get("/dog/1")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Pluto")));
	}

	@Test
	public void testPostDog() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.get("/dog")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(not(containsString("Chicco"))));

		mvc.perform(
						MockMvcRequestBuilders.post("/dog")
								.content(mapper.writeValueAsString(new Dog("Chicco", 1)))
								.contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Chicco")));

		mvc.perform(
						MockMvcRequestBuilders.get("/dog")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Chicco")));
	}

	@Test
	public void testPutDog() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.get("/dog/2")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Fido")));

		mvc.perform(
						MockMvcRequestBuilders.put("/dog/2")
								.content(mapper.writeValueAsString(new Dog("Fido jr", 2)))
								.contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().is2xxSuccessful());

		mvc.perform(
						MockMvcRequestBuilders.get("/dog/2")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Fido jr")));
	}

	@Test
	public void testDeleteDog() throws Exception {
		mvc.perform(
						MockMvcRequestBuilders.get("/dog/1")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Pluto")));

		mvc.perform(
						MockMvcRequestBuilders.delete("/dog/1")
				)
				.andExpect(status().is2xxSuccessful());

		mvc.perform(
						MockMvcRequestBuilders.get("/dog")
				)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(not(containsString("Pluto"))));
	}
}
