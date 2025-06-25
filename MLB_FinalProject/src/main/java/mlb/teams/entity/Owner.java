package mlb.teams.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Owner {
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ownerId;
	
	private String ownerName;
	private BigDecimal netWorth;
	
	public String getTeamName() {
		return team != null ? team.getTeamName() : null;
	}

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(optional=true)
	@JoinColumn(name = "teamId")
	@JsonBackReference
	private MLB team;
	
}
