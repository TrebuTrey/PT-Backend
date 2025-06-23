package mlb.teams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mlb.teams.entity.Concessions;
import mlb.teams.service.interfaces.ConcessionsService;
import mlb.teams.utility.MethodUtils;

@Service
public class ConcessionsServices {

	@Autowired
	private ConcessionsService concessionsService;

	@Transactional(readOnly = false)
	public Concessions saveConcessions(Concessions passedConcessions) {
		Long concessionsId = passedConcessions.getConcessionsId();
		
		Concessions concessions = findOrCreateConcessions(concessionsId);
		
		copyConcessionsFields(concessions, passedConcessions);
		
		return concessionsService.save(concessions);
	}
	
	private void copyConcessionsFields(Concessions savedConcessions, Concessions passedConcessions) {
		MethodUtils.copyIfNotNullOrZero(passedConcessions, savedConcessions, Concessions::getConcessionsName, Concessions::setConcessionsName);
		MethodUtils.copyIfNotNullOrZero(passedConcessions, savedConcessions, Concessions::getAvgCost, Concessions::setAvgCost);
		MethodUtils.copyIfNotNullOrZero(passedConcessions, savedConcessions, Concessions::getAvgConsumption, Concessions::setAvgConsumption);
	}

	private Concessions findOrCreateConcessions(Long concessionsId) {
		return MethodUtils.findOrCreateNew(concessionsService, concessionsId, Concessions::new);
	}
	
	private Concessions findConcessionsById(Long concessionsId) {
		return MethodUtils.findById(concessionsService, concessionsId, "Concessions");
	}

	public List<Concessions> retrieveAllConcessionss() {
		return concessionsService.findAll();
	}

	public Concessions retrieveConcessionsById(Long concessionsId) {
		return findConcessionsById(concessionsId);
	}

	public void deleteConcessionsById(Long concessionsId) {
		concessionsService.deleteById(concessionsId);
	}
}
