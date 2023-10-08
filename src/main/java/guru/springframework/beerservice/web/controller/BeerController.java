package guru.springframework.beerservice.web.controller;

import guru.springframework.beerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerId(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
