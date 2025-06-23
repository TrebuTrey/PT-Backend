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
import mlb.teams.entity.Owner;
import mlb.teams.service.OwnerServices;

@RestController
@RequestMapping("/mlb")
@Slf4j
public class OwnerController {

	@Autowired
	private OwnerServices ownerServices;
	
	@PostMapping("/owner")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Owner insertOwner(
			@RequestBody Owner ownerData) {
		log.info("creating Owner {}", ownerData);
		return ownerServices.saveOwner(ownerData);
	}
	
	@PutMapping("/owner/{ownerId}")
	public Owner updateMLBTeam(@PathVariable Long ownerId, 
			@RequestBody Owner ownerData) {
		ownerData.setOwnerId(ownerId);
		log.info("updating Owner with ID={}", ownerId);
		return ownerServices.saveOwner(ownerData);
	}
	
	@GetMapping("/owner")
	public List<Owner> retrieveAllOwners(){
		log.info("retrieving all owners");
		return ownerServices.retrieveAllOwners();
	}
	
	@GetMapping("/owner/{ownerId}")
	public Owner retrieveTeamById(@PathVariable Long ownerId){
		log.info("retrieving Owner with ID={}", ownerId);
		return ownerServices.retrieveOwnerById(ownerId);
	}
	
	@DeleteMapping("/owner/{ownerId}")
	public void deleteTeamById(@PathVariable Long ownerId) {
		log.info("deleting Owner with ID={}", ownerId);
		ownerServices.deleteOwnerById(ownerId);
	}
}
