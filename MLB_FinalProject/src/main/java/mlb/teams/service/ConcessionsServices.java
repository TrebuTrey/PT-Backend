package mlb.teams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import mlb.teams.entity.Concessions;
import mlb.teams.entity.Stadium;
import mlb.teams.service.interfaces.ConcessionsRepository;
import mlb.teams.utility.MethodUtils;

@Service
public class ConcessionsServices {

	@Autowired
	private ConcessionsRepository concessionsRepository;

	@Transactional(readOnly = false)
	public Concessions saveConcessions(Concessions passedConcessions) {
		Long concessionsId = passedConcessions.getConcessionsId();
		
		Concessions concessions = findOrCreateConcessions(concessionsId);
		
		copyConcessionsFields(concessions, passedConcessions);
		
		return concessionsRepository.save(concessions);
	}
	
	private void copyConcessionsFields(Concessions savedConcessions, Concessions passedConcessions) {
		MethodUtils.copyIfNotNullOrZero(passedConcessions, savedConcessions, Concessions::getConcessionsName, Concessions::setConcessionsName);
		MethodUtils.copyIfNotNullOrZero(passedConcessions, savedConcessions, Concessions::getAvgCost, Concessions::setAvgCost);
		MethodUtils.copyIfNotNullOrZero(passedConcessions, savedConcessions, Concessions::getAvgConsumption, Concessions::setAvgConsumption);
	}

	private Concessions findOrCreateConcessions(Long concessionsId) {
		return MethodUtils.findOrCreateNew(concessionsRepository, concessionsId, Concessions::new);
	}
	
	private Concessions findConcessionsById(Long concessionsId) {
		return MethodUtils.findById(concessionsRepository, concessionsId, "Concessions");
	}

	public List<Concessions> retrieveAllConcessionss() {
		return concessionsRepository.findAll();
	}

	public Concessions retrieveConcessionsById(Long concessionsId) {
		return findConcessionsById(concessionsId);
	}

	@Transactional
	public void deleteConcessionsById(Long concessionsId) {
	    Concessions concessions = concessionsRepository.findById(concessionsId)
	        .orElseThrow(() -> new EntityNotFoundException("Concessions not found"));

	    // Detach from all stadiums
	    for (Stadium s : concessions.getStadiums()) {
	        s.getConcessions().remove(concessions); // remove from stadium side
	    }
	    concessions.getStadiums().clear(); // remove from concessions side

	    concessionsRepository.delete(concessions);
	}
}
