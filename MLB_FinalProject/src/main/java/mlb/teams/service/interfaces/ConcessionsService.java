package mlb.teams.service.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mlb.teams.entity.Concessions;

public interface ConcessionsService extends JpaRepository<Concessions, Long> {


}
