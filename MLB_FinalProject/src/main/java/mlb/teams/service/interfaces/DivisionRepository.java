package mlb.teams.service.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mlb.teams.entity.Division;

public interface DivisionRepository extends JpaRepository<Division, Long> {


}
