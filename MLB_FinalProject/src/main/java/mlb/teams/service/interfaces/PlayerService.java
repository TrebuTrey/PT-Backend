package mlb.teams.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mlb.teams.entity.Player;

public interface PlayerService extends JpaRepository<Player, Long> {

	List<Player> findByPlayerLastNameIgnoreCase(String lastName);
}
