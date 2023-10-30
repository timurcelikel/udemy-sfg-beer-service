package guru.springframework.beerservice.service;

import guru.springframework.beerservice.domain.Beer;
import guru.springframework.beerservice.repositories.BeerRepository;
import guru.springframework.beerservice.web.exception.NotFoundException;
import guru.springframework.beerservice.web.mappers.BeerMapper;
import guru.springframework.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto findById(final UUID beerId) {
		return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(final BeerDto beerDto) {

		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(final UUID beerId, final BeerDto beerDto) {

		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beer.getBeerStyle());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beer.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}
}
