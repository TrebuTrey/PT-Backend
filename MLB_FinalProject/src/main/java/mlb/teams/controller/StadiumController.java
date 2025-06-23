package mlb.teams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mlb.teams.entity.Stadium;
import mlb.teams.service.StadiumServices;

@RestController
@RequestMapping("/mlb/stadium")
@Slf4j
public class StadiumController {

	@Autowired
	private StadiumServices stadiumServices;
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public Stadium insertStadium(
			@RequestBody Stadium stadiumData) {
		log.info("creating Stadium {}", stadiumData);
		return stadiumServices.saveStadium(stadiumData);
	}
	
	@PutMapping("/{stadiumId}")
	public Stadium updateStadium(@PathVariable Long stadiumId, 
			@RequestBody Stadium stadiumData) {
		stadiumData.setStadiumId(stadiumId);
		log.info("updating Stadium with ID={}", stadiumId);
		return stadiumServices.saveStadium(stadiumData);
	}
	
	@GetMapping()
	public List<Stadium> retrieveAllStadiums(){
		log.info("retrieving all stadiums");
		return stadiumServices.retrieveAllStadiums();
	}
	
	@GetMapping("/{stadiumId}")
	public Stadium retrieveStadiumById(@PathVariable Long stadiumId){
		log.info("retrieving Stadium with ID={}", stadiumId);
		return stadiumServices.retrieveStadiumById(stadiumId);
	}
	
	@DeleteMapping("/{stadiumId}")
	public void deleteStadiumById(@PathVariable Long stadiumId) {
		log.info("deleting Stadium with ID={}", stadiumId);
		stadiumServices.deleteStadiumById(stadiumId);
	}
}
