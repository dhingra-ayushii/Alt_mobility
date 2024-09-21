package server.alert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LowChargeFields extends AdditionalFields {
    @JsonProperty("min_cell_voltage")
    private Double minCellVoltage;

    @JsonProperty("last_seen")
    private LocalDateTime lastSeen;

    @JsonProperty("days_since")
    private Integer daysSince;
}
