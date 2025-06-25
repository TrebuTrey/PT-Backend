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
import mlb.teams.entity.MLB;
import mlb.teams.service.MLBServices;

@RestController
@RequestMapping("/mlb")
@Slf4j
public class MLBController {

	@Autowired
	private MLBServices mlbServices;
	
	@PostMapping("/team")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MLB insertMLBTeam(
			@RequestBody MLB mlbData) {
		log.info("creating MLB Team {}", mlbData);
		return mlbServices.saveMLBTeam(mlbData);
	}
	
	@PutMapping("/team/{mlbId}")
	public MLB updateMLBTeam(@PathVariable String mlbId, 
			@RequestBody MLB mlbData) {
		MLB mlb = mlbServices.findByNameOrAbbreviation(mlbId);
		mlbData.setTeamName(mlb.getTeamName());
		log.info("updating MLB Team with ID={}", mlbId);
		return mlbServices.saveMLBTeam(mlbData);
	}
	
	@PutMapping("/team/{teamName}/remove-player/{playerId}")
	public void removePlayerFromTeam(
	    @PathVariable String teamName,
	    @PathVariable Long playerId
	) {
		MLB team = mlbServices.findByNameOrAbbreviation(teamName);
	    mlbServices.removePlayerFromTeam(team, playerId);
	    log.info("Removing Player %s from MLB %s", playerId, teamName);
	}
	
	@GetMapping("/team")
	public List<MLB> retrieveAllTeams(){
		log.info("retrieving all teams");
		return mlbServices.retrieveAllTeams();
	}
	
	@GetMapping("/team/{mlbId}")
	public MLB retrieveTeamById(@PathVariable String mlbId){
		log.info("retrieving MLB Team with ID={}", mlbId);
		MLB mlb = mlbServices.findByNameOrAbbreviation(mlbId);
		return mlbServices.retrieveTeamById(mlb.getTeamName());
	}
	
	@DeleteMapping("/team/{mlbId}")
	public void deleteTeamById(@PathVariable String mlbId) {
		log.info("deleting MLB Team with ID={}", mlbId);
		MLB team = mlbServices.findByNameOrAbbreviation(mlbId);
		mlbServices.deleteTeamById(team);
	}
}
