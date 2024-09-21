package server.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.alert.model.InsuranceProvider;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InsuranceData {
    private InsuranceProvider insuranceProvider;
    private LocalDateTime dueDate;
    private Boolean renewalStatus;
    private Integer timeDue;
    private String policyNumber;
}
