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
import mlb.teams.entity.Player;
import mlb.teams.service.PlayerServices;

@RestController
@RequestMapping("/mlb")
@Slf4j
public class PlayerController {

	@Autowired
	private PlayerServices playerServices;
	
	@PostMapping("/player")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Player insertPlayer(
			@RequestBody Player playerData) {
		log.info("creating Player {}", playerData);
		return playerServices.savePlayer(playerData);
	}
	
	@PutMapping("/player/{playerId}")
	public Player updatePlayer(@PathVariable Long playerId, 
			@RequestBody Player playerData) {
		playerData.setPlayerId(playerId);
		log.info("updating Player with ID={}", playerId);
		return playerServices.savePlayer(playerData);
	}
	
	@GetMapping("/player")
	public List<Player> retrieveAllPlayers(){
		log.info("retrieving all player");
		return playerServices.retrieveAllPlayers();
	}
	
	@GetMapping("/player/{playerId}")
	public Player retrievePlayerById(@PathVariable Long playerId){
		log.info("retrieving Player with ID={}", playerId);
		return playerServices.retrievePlayerById(playerId);
	}
	
	@GetMapping("/player/lastname/{playerLastName}")
	public List<Player> retrievePlayerByLastName(@PathVariable String playerLastName){
		log.info("retrieving Player whose last name include={}", playerLastName);
		return playerServices.retrieveAllByLastName(playerLastName);
	}
	
	@DeleteMapping("/player/{playerId}")
	public void deletePlayerById(@PathVariable Long playerId) {
		log.info("deleting Player with ID={}", playerId);
		Player player = playerServices.retrievePlayerById(playerId);
		playerServices.deletePlayerById(player);
	}
}
