package com.weather.integrationTests;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.weather.model.Weather;
import com.weather.repository.WeatherRepository;

@DisplayName("WeatherControllerTest")
@SpringBootTest
@AutoConfigureMockMvc
class WeatherControllerTest {
	
	private static final Long EXISTENT_WEATHER_ID = 1L;
	private static final Long INEXISTENT_WEATHER_ID = 99L;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WeatherRepository repository;
	private Weather weatherWithId1;
	
	@BeforeEach
	void initData() {
		weatherWithId1 = Weather.builder()
							.description("Soleado")
							.build();
		repository.save(weatherWithId1);
		repository.save(new Weather());
		repository.save(new Weather());
	}
	
	@Test
	@DisplayName("Cuando busco un pronostico específico por id, obtengo dicha recurso correspondiente")
	void findByIdTest() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get("/api/v1/weather/{id}", EXISTENT_WEATHER_ID))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.description").value("Soleado"));
	}
	
	@Test
	@DisplayName("Cuando busco un pronostico específico por id y este no es encontrado el sistema devuelve Not Found error")
	void findByIdErrorTest() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get("/api/v1/weather/{id}", INEXISTENT_WEATHER_ID))
			.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("Cuando se busca una cantidad de pronosticos menor a 100 el sistema devuelve dichos pronósticos")
	void getLastForecastsTest() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get("/api/v1/weather/lastForecasts").param("limit", "3"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
	}
	
	@Test
	@DisplayName("Cuando se busca una cantidad de pronosticos mayor a 100 el sistema devuelve Bad Request")
	void getLastForecastsErrorTest() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get("/api/v1/weather/lastForecasts").param("limit", "101"))
			.andExpect(status().isBadRequest());
	}
}
