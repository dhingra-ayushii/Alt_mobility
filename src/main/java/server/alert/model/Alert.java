package server.alert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = AlertDeserializer.class)
public class Alert {
    @Id
    private String id;

    @JsonProperty("alert_type")
    private String alertType;

    @JsonProperty("vehicle_id")
    private String vehicleId;

    private String message;
    private String value;

    private String status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("additional_fields")
    private AdditionalFields additionalFields;

    private Double latitude;
    @JsonProperty("latitude_direction")
    private String latitudeDirection;
    private Double longitude;
    @JsonProperty("longitude_direction")
    private String longitudeDirection;
    private String severity;

    @JsonProperty("service_request_id")
    private String serviceRequestId;

    @JsonProperty("date_value")
    private LocalDateTime dateValue;

    @JsonProperty("freshdesk_ticket_id")
    private String freshdeskTicketId;

    @JsonProperty("threshold_id")
    private String thresholdId;

}
