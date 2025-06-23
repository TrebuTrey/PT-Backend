package mlb.teams.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mlb.teams.entity.Concessions;
import mlb.teams.entity.Stadium;
import mlb.teams.service.interfaces.StadiumService;
import mlb.teams.utility.MethodUtils;

@Service
public class StadiumServices {

	@Autowired
	private StadiumService stadiumService;
	
	@Autowired
	private MLBServices mlbServices;
	
	@Autowired
	private ConcessionsServices conServices;

	@Transactional(readOnly = false)
	public Stadium saveStadium(Stadium passedStadium) {
		Long stadiumId = passedStadium.getStadiumId();
		
		Stadium stadium = findOrCreateStadium(stadiumId);
		
		copyStadiumFields(stadium, passedStadium);
		
		return stadiumService.save(stadium);
	}
	
	private void copyStadiumFields(Stadium savedStadium, Stadium passedStadium) {
		// Only update team if provided AND has valid teamName
	    if (passedStadium.getTeam() != null && 
	        passedStadium.getTeam().getTeamName() != null && 
	        !passedStadium.getTeam().getTeamName().isBlank()) {
	        
	        savedStadium.setTeam(
	            mlbServices.retrieveTeamById(passedStadium.getTeam().getTeamName())
	        );
	    }
		addConcessionsToStadium(savedStadium, passedStadium);
		MethodUtils.copyIfNotNullOrZero(passedStadium, savedStadium, Stadium::getStadiumName, Stadium::setStadiumName);
		MethodUtils.copyIfNotNullOrZero(passedStadium, savedStadium, Stadium::getStadiumAddress, Stadium::setStadiumAddress);
		MethodUtils.copyIfNotNullOrZero(passedStadium, savedStadium, Stadium::getStadiumCapacity, Stadium::setStadiumCapacity);
		MethodUtils.copyIfNotNullOrZero(passedStadium, savedStadium, Stadium::getYearFounded, Stadium::setYearFounded);
		
	}

	private Stadium findOrCreateStadium(Long stadiumId) {
		return MethodUtils.findOrCreateNew(stadiumService, stadiumId, Stadium::new);
	}
	
	private Stadium findStadiumById(Long stadiumId) {
		return MethodUtils.findById(stadiumService, stadiumId, "Stadium");
	}

	public List<Stadium> retrieveAllStadiums() {
		return stadiumService.findAll();
	}

	public Stadium retrieveStadiumById(Long stadiumId) {
		return findStadiumById(stadiumId);
	}

	public void deleteStadiumById(Long stadiumId) {
		stadiumService.deleteById(stadiumId);
	}
	
	public void addConcessionsToStadium(Stadium savedStadium, Stadium passedStadium) {
		Set<Concessions> currentConcessions = savedStadium.getConcessions();
		Set<Concessions> updatedConcessions = passedStadium.getConcessions();
		
		for(Concessions c: updatedConcessions) {
			Concessions cId = conServices.retrieveConcessionsById(c.getConcessionsId());
			if(!currentConcessions.contains(c) && cId.getConcessionsId() != null) {
				currentConcessions.add(cId);
			}
		}
		
		savedStadium.setConcessions(currentConcessions);
	}
}
