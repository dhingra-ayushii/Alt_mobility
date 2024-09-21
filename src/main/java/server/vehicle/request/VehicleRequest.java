package server.vehicle.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.vehicle.model.InsuranceData;
import server.vehicle.model.Vehicle;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleRequest {
    private String id;
    private InsuranceData insurance;

    public VehicleRequest(Vehicle vehicle) {
    }
}
