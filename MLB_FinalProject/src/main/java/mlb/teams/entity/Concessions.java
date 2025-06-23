package mlb.teams.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Concessions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long concessionsId;
	
	private String concessionsName;
	private BigDecimal avgCost;
	private BigDecimal avgConsumption;
	
	public List<String> getStadiumNames(){
		List<String> stadiumNames = new ArrayList<>();
		
		if(stadiums != null) {
			for(Stadium stad: stadiums) {
				stadiumNames.add(stad.getStadiumName());
			}
		}
		
		return stadiumNames;
	}
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "concessions", cascade = CascadeType.PERSIST)
	@JsonIgnore
	Set<Stadium> stadiums = new HashSet<>();
}
