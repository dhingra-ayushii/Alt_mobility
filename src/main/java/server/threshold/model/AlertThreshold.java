package server.threshold.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.alert.model.AlertType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertThreshold {
    @Id
    private String id;
    private String vehicleId;
    private AlertType alertType;
    private String alertName;
    private List<Threshold> thresholds;
    private String siverity;
}
