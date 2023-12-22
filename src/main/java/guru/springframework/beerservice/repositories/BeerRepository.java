package guru.springframework.beerservice.repositories;

import guru.springframework.beerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

	// Below is not needed as it overrides one of the methods we get for free from JpaRepository
	//Optional<Beer> findById(UUID beerId);

}
