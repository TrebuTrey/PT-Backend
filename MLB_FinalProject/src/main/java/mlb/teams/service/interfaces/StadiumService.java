package mlb.teams.service.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mlb.teams.entity.Stadium;

public interface StadiumService extends JpaRepository<Stadium, Long> {


}
