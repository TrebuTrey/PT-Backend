package mlb.teams.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Stadium {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stadiumId;
	
	private String stadiumName;
	private String stadiumAddress;
	private int stadiumCapacity;
	private Date yearFounded;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(optional=true)
	@JoinColumn(name = "teamId")
	@JsonBackReference
	private MLB team;
	
	public String getTeamName() {
		return team != null ? team.getTeamName(): null;
	}
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "stadium_concessions", 
		joinColumns = @JoinColumn(name = "stadium_id"), 
		inverseJoinColumns = @JoinColumn(name = "concessions_id")
	)
	@JsonIgnoreProperties({"avgCost", "avgConsumption", "stadiumNames"})
	Set<Concessions> concessions = new HashSet<>();
}
