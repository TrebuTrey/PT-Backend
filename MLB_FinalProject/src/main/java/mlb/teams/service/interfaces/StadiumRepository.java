package mlb.teams.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mlb.teams.entity.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {

	@Query(value = "SELECT stadium_id, concessions_id FROM stadium_concessions", nativeQuery = true)
	List<Object[]> findAllStadiumConcessionPairs();


}
