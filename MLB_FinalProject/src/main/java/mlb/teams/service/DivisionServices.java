package mlb.teams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mlb.teams.entity.Division;
import mlb.teams.service.interfaces.DivisionService;
import mlb.teams.utility.MethodUtils;

@Service
public class DivisionServices {

	@Autowired
	private DivisionService divisionService;

	public Division saveDivision(Division newDivData) {
		Long divId = newDivData.getDivisionId();
		
		Division div = findOrCreateDivision(divId);
		
		copyFormFields(div, newDivData);
		
		return divisionService.save(div);
	}
	
	private void copyFormFields(Division targetDiv, Division sourceDiv) {
		MethodUtils.copyIfNotNullOrZero(sourceDiv, targetDiv, Division::getDivisionName, Division::setDivisionName);
	}

	private Division findOrCreateDivision(Long divId) {
		return MethodUtils.findOrCreateNew(divisionService, divId, Division::new);
	}
	
	public Division retrieveDivById(Long divId) {
		return MethodUtils.findById(divisionService, divId, "Division");
	}

	public List<Division> retrieveAllDivisions() {
		return divisionService.findAll();
	}

	public void deleteDivision(Long divId) {
		divisionService.deleteById(divId);
	}
	
	
}
