package server.alert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeepDischargeFields extends AdditionalFields {
    @JsonProperty("idle_days")
    private Integer idleDays;

    @JsonProperty("min_cell_voltage")
    private Double minCellVoltage;

    @JsonProperty("last_soc")
    private Double lastSoc;
}
