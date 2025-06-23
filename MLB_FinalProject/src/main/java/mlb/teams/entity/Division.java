package mlb.teams.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Division {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long divisionId;
	
	private String divisionName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="div")
	@JsonManagedReference
	@JsonIgnoreProperties({"divisionName", "stadium", "owner", "players"})
	Set<MLB> teams = new HashSet<>();
}
