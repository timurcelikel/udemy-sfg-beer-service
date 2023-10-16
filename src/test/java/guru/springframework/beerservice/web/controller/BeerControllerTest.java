package guru.springframework.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.beerservice.web.model.BeerDto;
import guru.springframework.beerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@AutoConfigureMockMvc
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getBeerId() throws Exception {
		mockMvc.perform(
				get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}

	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto =
				BeerDto.builder()
						.beerName("Punkin Drublic")
						.beerStyle(BeerStyle.ALE)
						.upc(125345334643L)
						.price(new BigDecimal("2.55")).build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		mockMvc.perform(post("/api/v1/beer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(status().isCreated());
	}

	@Test
	void updateBeerById() throws Exception {
		BeerDto beerDto = BeerDto.builder()
				.beerName("Punkin Drublic")
				.beerStyle(BeerStyle.ALE)
				.upc(125345334643L)
				.price(new BigDecimal("2.55")).build();
		
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
						.contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(status().isNoContent());
	}
}