package mlb.teams.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mlb.teams.entity.Concessions;
import mlb.teams.entity.Stadium;
import mlb.teams.service.interfaces.ConcessionsRepository;
import mlb.teams.service.interfaces.StadiumRepository;
import mlb.teams.utility.MethodUtils;

@Service
public class StadiumServices {

	@Autowired
	private StadiumRepository stadiumRepository;
	
	@Autowired
	private MLBServices mlbServices;
	
	@Autowired
	private ConcessionsRepository conRepository;

	@Transactional(readOnly = false)
	public Stadium saveStadium(Stadium passedStadium) {
		Long stadiumId = passedStadium.getStadiumId();
		
		Stadium stadium = findOrCreateStadium(stadiumId);
		
		copyStadiumFields(stadium, passedStadium);
		
		return stadiumRepository.save(stadium);
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
		return MethodUtils.findOrCreateNew(stadiumRepository, stadiumId, Stadium::new);
	}
	
	private Stadium findStadiumById(Long stadiumId) {
		return MethodUtils.findById(stadiumRepository, stadiumId, "Stadium");
	}

	public List<Stadium> retrieveAllStadiums() {
		return stadiumRepository.findAll();
	}

	public Stadium retrieveStadiumById(Long stadiumId) {
		return findStadiumById(stadiumId);
	}

	@Transactional
	public void deleteStadiumById(Stadium stadium) {
	    // Detach from team
	    if (stadium.getTeam() != null) {
	        stadium.getTeam().setStadium(null); // bidirectional unlink
	        stadium.setTeam(null);
	    }

	    // Detach from concessions (Many-to-Many)
	    if (stadium.getConcessions() != null) {
	        for (Concessions c : stadium.getConcessions()) {
	            c.getStadiums().remove(stadium); // unlink from Concessions side
	        }
	        stadium.getConcessions().clear(); // unlink from Stadium side
	    }

	    stadiumRepository.deleteById(stadium.getStadiumId());
	}
	
	@Transactional
	public void addConcessionsToStadium(Stadium savedStadium, Stadium passedStadium) {
		Set<Concessions> currentConcessions = savedStadium.getConcessions();
		Set<Concessions> updatedConcessions = passedStadium.getConcessions();
		
		for(Concessions c: updatedConcessions) {
			Concessions cId =conRepository.findById(c.getConcessionsId()).orElseThrow(() -> 
    		new NoSuchElementException("Concessions does not exist"));
			
			if(!currentConcessions.contains(c) && cId.getConcessionsId() != null) {
				currentConcessions.add(cId);
			}
		}
		
		savedStadium.setConcessions(currentConcessions);
	}
	
	public List<Map<String, Long>> retrieveStadiumConcessionPairs() {
	    List<Object[]> rows = stadiumRepository.findAllStadiumConcessionPairs();

	    return rows.stream().map(row -> {
	        Map<String, Long> map = new HashMap<>();
	        map.put("stadiumId", ((Number) row[0]).longValue());
	        map.put("concessionsId", ((Number) row[1]).longValue());
	        return map;
	    }).collect(Collectors.toList());
	}
	
	@Transactional
	public Stadium removeConcessionsFromStadium(Long stadiumId, Long concessionsId) {
	    Stadium stadium = retrieveStadiumById(stadiumId);

	    Concessions concession = conRepository.findById(concessionsId).orElseThrow(() -> 
	    		new NoSuchElementException("Concessions does not exist"));

	    stadium.getConcessions().remove(concession);

	    return stadiumRepository.save(stadium); // update the relationship
	}

}
