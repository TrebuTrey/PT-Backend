package mlb.teams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mlb.teams.entity.MLB;
import mlb.teams.service.interfaces.MLBService;
import mlb.teams.utility.MethodUtils;

@Service
public class MLBServices {

	@Autowired
	private MLBService mlbService;
	
	@Autowired
	private DivisionServices divisionServices;

	@Transactional(readOnly = false)
	public MLB saveMLBTeam(MLB passedTeam) {
		String mlbId = passedTeam.getTeamName();
		
		MLB mlb = findOrCreateTeam(mlbId);
		
		copyTeamFields(mlb, passedTeam);
		
		return mlbService.save(mlb);
	}
	
	private void copyTeamFields(MLB savedTeam, MLB passedTeam) {
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamName, MLB::setTeamName);
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamAbbreviation, MLB::setTeamAbbreviation);
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamCity, MLB::setTeamCity);
		MethodUtils.copyIfNotNullOrZero(passedTeam, savedTeam, MLB::getTeamState, MLB::setTeamState);
		if(passedTeam.getDiv() != null) savedTeam.setDiv(divisionServices.retrieveDivById(passedTeam.getDiv().getDivisionId()));
	}

	private MLB findOrCreateTeam(String mlbId) {
		return MethodUtils.findOrCreateNew(mlbService, mlbId, MLB::new);
	}
	
	private MLB findTeamById(String mlbId) {
		return MethodUtils.findById(mlbService, mlbId, "Team");
	}

	public List<MLB> retrieveAllTeams() {
		return mlbService.findAll();
	}

	public MLB retrieveTeamById(String mlbId) {
		return findTeamById(mlbId);
	}

	public void deleteTeamById(String mlbId) {
		mlbService.deleteById(mlbId);
	}
	
	public MLB findByNameOrAbbreviation(String input) {
	    return mlbService.findByTeamName(input)
	            .or(() -> mlbService.findByTeamAbbreviation(input))
	            .orElse(null); // or throw an exception if preferred
	}
}
