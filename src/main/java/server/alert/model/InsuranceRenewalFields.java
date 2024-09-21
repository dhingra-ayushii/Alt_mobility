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
public class InsuranceRenewalFields extends AdditionalFields {
    @JsonProperty("insurance_provider")
    private InsuranceProvider insuranceProvider;

    @JsonProperty("due_date")
    private LocalDateTime dueDate;

    @JsonProperty("renewal_status")
    private Boolean renewalStatus;

    @JsonProperty("time_due")
    private Integer timeDue;

    @JsonProperty("policy_number")
    private String policyNumber;
}
