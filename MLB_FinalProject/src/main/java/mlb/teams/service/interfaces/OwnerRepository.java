package mlb.teams.service.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import mlb.teams.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {


}
