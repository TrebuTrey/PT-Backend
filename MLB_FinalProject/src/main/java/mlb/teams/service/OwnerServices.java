package mlb.teams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mlb.teams.entity.Owner;
import mlb.teams.service.interfaces.OwnerService;
import mlb.teams.utility.MethodUtils;

@Service
public class OwnerServices {

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private MLBServices mlbServices;

	@Transactional(readOnly = false)
	public Owner saveOwner(Owner passedOwner) {
		Long ownerId = passedOwner.getOwnerId();
		
		Owner owner = findOrCreateOwner(ownerId);
		
		copyOwnerFields(owner, passedOwner);
		
		return ownerService.save(owner);
	}
	
	private void copyOwnerFields(Owner savedOwner, Owner passedOwner) {
		MethodUtils.copyIfNotNullOrZero(passedOwner, savedOwner, Owner::getOwnerName, Owner::setOwnerName);
		MethodUtils.copyIfNotNullOrZero(passedOwner, savedOwner, Owner::getNetWorth, Owner::setNetWorth);
		if(passedOwner.getTeam() != null) savedOwner.setTeam(mlbServices.retrieveTeamById(passedOwner.getTeam().getTeamName()));
	}

	private Owner findOrCreateOwner(Long ownerId) {
		return MethodUtils.findOrCreateNew(ownerService, ownerId, Owner::new);
	}
	
	private Owner findOwnerById(Long ownerId) {
		return MethodUtils.findById(ownerService, ownerId, "Owner");
	}

	public List<Owner> retrieveAllOwners() {
		return ownerService.findAll();
	}

	public Owner retrieveOwnerById(Long ownerId) {
		return findOwnerById(ownerId);
	}

	public void deleteOwnerById(Long ownerId) {
		ownerService.deleteById(ownerId);
	}
}
