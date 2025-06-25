package mlb.teams.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mlb.teams.entity.Division;
import mlb.teams.entity.MLB;
import mlb.teams.service.interfaces.DivisionRepository;
import mlb.teams.service.interfaces.MLBRepository;
import mlb.teams.utility.MethodUtils;

@Service
public class DivisionServices {

	@Autowired
	private DivisionRepository divisionRepository;
	
	@Autowired
	private MLBRepository mlbRepository;

	public Division saveDivision(Division newDivData) {
		Long divId = newDivData.getDivisionId();
		
		Division div = findOrCreateDivision(divId);
		
		copyFormFields(div, newDivData);
		
		return divisionRepository.save(div);
	}
	
	private void copyFormFields(Division targetDiv, Division sourceDiv) {
		MethodUtils.copyIfNotNullOrZero(sourceDiv, targetDiv, Division::getDivisionName, Division::setDivisionName);
	}

	private Division findOrCreateDivision(Long divId) {
		return MethodUtils.findOrCreateNew(divisionRepository, divId, Division::new);
	}
	
	public Division retrieveDivById(Long divId) {
		return MethodUtils.findById(divisionRepository, divId, "Division");
	}

	public List<Division> retrieveAllDivisions() {
		return divisionRepository.findAll();
	}
	
	public void addTeamToDivision(Division div, String mlbId) {
		MLB team = mlbRepository.findByTeamAbbreviation(mlbId).orElse(
				mlbRepository.findById(mlbId).orElseThrow( () -> 
				new NoSuchElementException("MLB team does not exist.")));
		
		if(!div.getTeams().contains(team)) {
			team.setDiv(div);
			mlbRepository.save(team);
		}
		
		divisionRepository.save(div);
	}

	public void deleteDivision(Division div) {
		for(MLB team: div.getTeams()) {
			team.setDiv(null);
		}
		
		divisionRepository.deleteById(div.getDivisionId());
	}
	
	
}
