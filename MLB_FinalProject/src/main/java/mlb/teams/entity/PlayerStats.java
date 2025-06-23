package mlb.teams.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStats {

    private BigDecimal avg;
    private BigDecimal obp;
    private BigDecimal slg;
    private BigDecimal opsPlus;
    
}
