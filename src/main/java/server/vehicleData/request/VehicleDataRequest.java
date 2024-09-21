package server.vehicleData.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleDataRequest {

    private boolean iotConnected;

    private Integer batteryPercentage;

    private String driveMode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String imei;

    private String ambientTemp;

    private String batteryCurrent;

    private String batteryEfficiency;

    private String batteryHealth;

    private String batteryTemp;

    private String batteryUsage;

    private String batteryVitalScore;

    private String behaviorAcceleration;

    private String behaviorBreaking;

    private String behaviorLastUpdated;

    private String behaviorOverspeed;

    private String chargeCycles;

    private Double co2Saved;

    private String controllerTemp;

    private String distanceToEmpty;

    private String estResaleVal;

    private Double fuelSaved;

    private LocalDateTime lastSeenAt;

    private Double latitude;

    private String leaseStartReading;

    private LocalDateTime locationLastUpdated;

    private Double longitude;

    private String motorTemp;

    private String remainingBatteryCapacity;

    private String resaleValComparison;

    private String totalBatteryCapacity;

    private Double totalEnergy;

    private String vehicleCondition;

    private String vehicleEfficiency;

    private boolean charging;

    private Integer lastSpeed;

    private Integer maxSpeed;

    private String stopsForTheDay;

    private Double totalOperationalHours;

    private String batteryVoltage;

    private Double distanceTravelledToday;

    private Double totalOdometer;

    private String vehicleId;

    private Integer dailyAvgSpeed;

    private Integer dailySpeedCount;

    private Double monthlyRuntime;

    private Double monthlyDistanceTravelled;

    private String latitudeDirection;

    private String longitudeDirection;

    private Double dailyOdometerStart;

    private String additionalFields;

    private Double averageBatteryTemp;

    private Integer totalChargeCurrent;

    private Integer totalChargeCurrentAtHighTemperature;

    private Integer totalDischargeCurrent;

    private Integer totalDischargeCurrentAtHighTemperature;


}
