package mlb.teams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import mlb.teams.entity.MLB;
import mlb.teams.entity.Player;
import mlb.teams.service.interfaces.MLBRepository;
import mlb.teams.service.interfaces.PlayerRepository;
import mlb.teams.utility.MethodUtils;

@Service
public class MLBServices {

	@Autowired
	private MLBRepository mlbRepository;
	
	@Autowired 
	PlayerRepository playerRepository;
	
	@Autowired
	private DivisionServices divisionServices;

	@Transactional(readOnly = false)
	public MLB saveMLBTeam(MLB passedTeam) {
		String mlbId = passedTeam.getTeamName();
		
		MLB mlb = findOrCreateTeam(mlbId);
		
		copyTeamFields(mlb, passedTeam);
		
		return mlbRepository.save(mlb);
	}
	
	private void copyTeamFields(MLB savedTeam, MLB passedTeam) {
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamName, MLB::setTeamName);
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamAbbreviation, MLB::setTeamAbbreviation);
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamCity, MLB::setTeamCity);
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamState, MLB::setTeamState);
		if(passedTeam.getDiv() != null) savedTeam.setDiv(divisionServices.retrieveDivById(passedTeam.getDiv().getDivisionId()));
	}

	private MLB findOrCreateTeam(String mlbId) {
		return MethodUtils.findOrCreateNew(mlbRepository, mlbId, MLB::new);
	}
	
	private MLB findTeamById(String mlbId) {
		return MethodUtils.findById(mlbRepository, mlbId, "Team");
	}

	public List<MLB> retrieveAllTeams() {
		return mlbRepository.findAll();
	}

	public MLB retrieveTeamById(String mlbId) {
		return findTeamById(mlbId);
	}

	@Transactional
	public void deleteTeamById(MLB team) {
		// Break relationships manually
	    for (Player player : team.getPlayers()) {
	        player.setTeam(null);
	    }
	    team.getPlayers().clear();

	    if (team.getStadium() != null) {
	        team.getStadium().setTeam(null);
	        team.setStadium(null);
	    }

	    if (team.getOwner() != null) {
	        team.getOwner().setTeam(null);
	        team.setOwner(null);
	    }

	    team.setDiv(null); // Break link to Division

	    mlbRepository.delete(team);
//		mlbRepository.deleteById(team.getTeamName());
	}
	
	public MLB findByNameOrAbbreviation(String input) {
	    return mlbRepository.findByTeamName(input)
	            .or(() -> mlbRepository.findByTeamAbbreviation(input))
	            .orElse(null); // or throw an exception if preferred
	}
	
	@Transactional
	public void removePlayerFromTeam(MLB team, Long playerId) {
		Player player = playerRepository.findById(playerId)
		        .orElseThrow(() -> new EntityNotFoundException("Player not found"));
		
		if(team.getPlayers().contains(player)) {
			player.setTeam(null);
			playerRepository.save(player);
		}
		
		mlbRepository.save(team);
	}
	
	@Transactional
	private void removeAllPlayersFromTeam(MLB team) {
		for(Player player: team.getPlayers()) {
			player.setTeam(null);
			playerRepository.save(player);
		}
		
		mlbRepository.save(team);
	}
}
