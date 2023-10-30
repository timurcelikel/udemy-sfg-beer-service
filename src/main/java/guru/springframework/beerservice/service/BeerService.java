package guru.springframework.beerservice.service;

import guru.springframework.beerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

	BeerDto findById(UUID beerId);
	BeerDto saveNewBeer(BeerDto beerDto);
	BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
