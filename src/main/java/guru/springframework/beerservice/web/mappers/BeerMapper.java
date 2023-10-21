package guru.springframework.beerservice.web.mappers;

import guru.springframework.beerservice.domain.Beer;
import guru.springframework.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);
	Beer beerDtoToBeer(BeerDto beerDto);
}
