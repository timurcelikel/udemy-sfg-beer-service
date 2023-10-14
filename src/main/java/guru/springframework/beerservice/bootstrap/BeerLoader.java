package guru.springframework.beerservice.bootstrap;

import guru.springframework.beerservice.domain.Beer;
import guru.springframework.beerservice.repositories.BeerRepository;
import guru.springframework.beerservice.web.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;

	public BeerLoader(final BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(final String... args) throws Exception {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
					.beerName("Mango Bobs")
					.beerStyle(BeerStyle.IPA)
					.quantityToBrew(200)
					.upc(337574358735L)
					.price(new BigDecimal("2.75"))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Galaxy Cat")
					.beerStyle(BeerStyle.ALE)
					.quantityToBrew(200)
					.upc(3375743583536L)
					.price(new BigDecimal("2.41"))
					.build());
		}
		log.info("Loaded Beers: " + beerRepository.count());
	}
}
