package mlb.teams.service.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mlb.teams.entity.MLB;

public interface MLBService extends JpaRepository<MLB, String> {
	Optional<MLB> findByTeamName(String teamName);
	Optional<MLB> findByTeamAbbreviation(String teamAbbreviation);
}
