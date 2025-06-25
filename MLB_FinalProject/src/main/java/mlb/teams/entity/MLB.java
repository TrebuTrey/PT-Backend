package mlb.teams.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class MLB {
	@Id
	private String teamName;
	
	@Column(unique = true)
	private String teamAbbreviation;
	
	private String teamCity;
	private String teamState;
	
	public String getDivisionName() {
		return div != null ? div.getDivisionName(): null;
	}
	
	public PlayerStats getTeamStats() {
	    BigDecimal totalAvg = BigDecimal.ZERO;
	    BigDecimal totalObp = BigDecimal.ZERO;
	    BigDecimal totalSlg = BigDecimal.ZERO;
	    BigDecimal totalOpsPlus = BigDecimal.ZERO;

	    int count = 0;

	    for (Player player : players) {
	        PlayerStats stats = player.getStats();
	        if (stats != null && !player.getPlayerPosition().equalsIgnoreCase("P")) {
	            totalAvg = totalAvg.add(defaultZero(stats.getAvg()));
	            totalObp = totalObp.add(defaultZero(stats.getObp()));
	            totalSlg = totalSlg.add(defaultZero(stats.getSlg()));
	            totalOpsPlus = totalOpsPlus.add(defaultZero(stats.getOpsPlus()));
	            count += 1;
	        }
	    }

	    if (count == 0) return new PlayerStats();

	    // Average each stat across players
	    BigDecimal divisor = new BigDecimal(count);
	    return new PlayerStats(
	        totalAvg.divide(divisor, 3, RoundingMode.HALF_UP),
	        totalObp.divide(divisor, 3, RoundingMode.HALF_UP),
	        totalSlg.divide(divisor, 3, RoundingMode.HALF_UP),
	        totalOpsPlus.divide(divisor, 3, RoundingMode.HALF_UP)
	    );
	}

	private BigDecimal defaultZero(BigDecimal value) {
	    return value != null ? value : BigDecimal.ZERO;
	}
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "team")
	@JsonManagedReference
	@JsonIgnoreProperties({"teamName", "playerAge", "stats"})
	Set<Player> players = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "divisionId")
	@JsonBackReference
	private Division div;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(mappedBy = "team")
	@JsonManagedReference
	@JsonIgnoreProperties({"teamName"})
	Owner owner;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(mappedBy = "team")
	@JsonManagedReference
	@JsonIgnoreProperties({"teamName", "concessions"})
	Stadium stadium;
}

