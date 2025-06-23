package mlb.teams.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Player {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long playerId;
	
	private String playerFirstName;
	private String playerLastName;
	private Long playerAge;
	private Long uniformNumber;
	private String playerPosition;
	
	@Embedded
	private PlayerStats stats;
	
	public String getTeamName() {
		return team != null ? team.getTeamName(): null;
	}
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "teamId", referencedColumnName = "teamName")
	@JsonBackReference
	private MLB team;
	
}
