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
import mlb.teams.entity.Concessions;
import mlb.teams.service.ConcessionsServices;

@RestController
@RequestMapping("/mlb")
@Slf4j
public class ConcessionsController {

	@Autowired
	private ConcessionsServices concessionsServices;
	
	@PostMapping("/concessions")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Concessions insertConcessions(
			@RequestBody Concessions concessionsData) {
		log.info("creating Concessions {}", concessionsData);
		return concessionsServices.saveConcessions(concessionsData);
	}
	
	@PutMapping("/concessions/{concessionsId}")
	public Concessions updateConcessions(@PathVariable Long concessionsId, 
			@RequestBody Concessions concessionsData) {
		concessionsData.setConcessionsId(concessionsId);
		log.info("updating Concessions with ID={}", concessionsId);
		return concessionsServices.saveConcessions(concessionsData);
	}
	
	@GetMapping("/concessions")
	public List<Concessions> retrieveAllConcessionss(){
		log.info("retrieving all concessionss");
		return concessionsServices.retrieveAllConcessionss();
	}
	
	@GetMapping("/concessions/{concessionsId}")
	public Concessions retrieveConcessionsById(@PathVariable Long concessionsId){
		log.info("retrieving Concessions with ID={}", concessionsId);
		return concessionsServices.retrieveConcessionsById(concessionsId);
	}
	
	@DeleteMapping("/concessions/{concessionsId}")
	public void deleteConcessionsById(@PathVariable Long concessionsId) {
		log.info("deleting Concessions with ID={}", concessionsId);
		concessionsServices.deleteConcessionsById(concessionsId);
	}
}
