package guru.springframework.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.beerservice.service.BeerService;
import guru.springframework.beerservice.web.model.BeerDto;
import guru.springframework.beerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@AutoConfigureMockMvc
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BeerService beerService;

	@Test
	void getBeerId() throws Exception {

		given(beerService.findById(any())).willReturn(getValidBeerDto());

		mockMvc.perform(
				get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isOk());
	}

	@Test
	void saveNewBeer() throws Exception {

		String beerDtoJson = objectMapper.writeValueAsString(getValidBeerDto());

		given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

		mockMvc.perform(post("/api/v1/beer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(status().isCreated());
	}

	@Test
	void updateBeerById() throws Exception {

		String beerDtoJson = objectMapper.writeValueAsString(getValidBeerDto());

		given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
						.contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(status().isNoContent());
	}

	private BeerDto getValidBeerDto() {
		return
				BeerDto.builder()
						.beerName("Punkin Drublic")
						.beerStyle(BeerStyle.ALE)
						.upc("125345334643")
						.price(new BigDecimal("2.55")).build();
	}

}