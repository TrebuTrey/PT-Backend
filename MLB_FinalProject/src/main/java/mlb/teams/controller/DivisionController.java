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
import mlb.teams.entity.Division;
import mlb.teams.service.DivisionServices;

@Slf4j
@RestController
@RequestMapping("/mlb")
public class DivisionController {

	@Autowired
	private DivisionServices divServices;
	
	@PostMapping("/division")
	@ResponseStatus(HttpStatus.CREATED)
	private Division createDivision(
			@RequestBody Division divisionData) {
		return divServices.saveDivision(divisionData);
	}
	
	@PutMapping("/division/{divId}")
	private Division updateDivision(
			@RequestBody Division divisionData, @PathVariable Long divId) {
		divisionData.setDivisionId(divId);
		log.info("Updating Division with ID={}", divId);
		return divServices.saveDivision(divisionData);
	}
	
	@GetMapping("/division")
	private List<Division> retrieveDivisions(){
		return divServices.retrieveAllDivisions();
	}
	
	@GetMapping("/division/{divId}")
	private Division retrieveDivisions(@PathVariable Long divId){
		return divServices.retrieveDivById(divId);
	}
	
	@DeleteMapping("/division/{divId}")
	private void deleteDivision(@PathVariable Long divId) {
		divServices.deleteDivision(divId);
	}
	
	
}
