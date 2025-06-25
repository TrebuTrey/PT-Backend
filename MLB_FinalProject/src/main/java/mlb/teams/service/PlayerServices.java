package mlb.teams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import mlb.teams.entity.Player;
import mlb.teams.service.interfaces.PlayerRepository;
import mlb.teams.utility.MethodUtils;

@Service
public class PlayerServices {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MLBServices mlbServices;

	@Transactional(readOnly = false)
	public Player savePlayer(Player updatedPlayer) {
		Long playerId = updatedPlayer.getPlayerId();
		
		Player player = findOrCreatePlayer(playerId);
		
		copyPlayerFields(player, updatedPlayer);
		
		return playerRepository.save(player);
	}
	
	private void copyPlayerFields(Player savePlayer, Player passedPlayer) {
		if(passedPlayer.getTeam() != null) savePlayer.setTeam(mlbServices.retrieveTeamById(passedPlayer.getTeam().getTeamName()));
		
		//Method Reference
		MethodUtils.copyIfNotNullOrZero(passedPlayer, savePlayer, Player::getPlayerFirstName, Player::setPlayerFirstName);
		MethodUtils.copyIfNotNullOrZero(passedPlayer, savePlayer, Player::getPlayerLastName, Player::setPlayerLastName);
		MethodUtils.copyIfNotNullOrZero(passedPlayer, savePlayer, Player::getPlayerAge, Player::setPlayerAge);
		MethodUtils.copyIfNotNullOrZero(passedPlayer, savePlayer, Player::getPlayerPosition, Player::setPlayerPosition);
		MethodUtils.copyIfNotNull(passedPlayer, savePlayer, Player::getUniformNumber, Player::setUniformNumber);
		MethodUtils.copyIfNotNull(passedPlayer, savePlayer, Player::getStats, Player::setStats);
	}

	private Player findOrCreatePlayer(Long playerId) {
		return MethodUtils.findOrCreateNew(playerRepository, playerId, Player::new);
	}
	
	private Player findPlayerById(Long playerId) {
		return MethodUtils.findById(playerRepository, playerId, "Player");
	}

	public List<Player> retrieveAllPlayers() {
		return playerRepository.findAll();
	}
	
	public List<Player> retrieveAllByLastName(String lastName) {
		return playerRepository.findByPlayerLastNameIgnoreCase(lastName);
	}

	public Player retrievePlayerById(Long playerId) {
		return findPlayerById(playerId);
	}

	public void deletePlayerById(Player player) {
		
		if(player.getTeam() != null) {
			player.setTeam(null);
		}
		
		playerRepository.deleteById(player.getPlayerId());
	}
}
