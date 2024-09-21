package server.vehicleData.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.AuditModel;
import server.vehicleData.request.VehicleDataRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleData extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dbId;
    private String id;
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


    public VehicleData(VehicleDataRequest request) {
        if (request != null) {
            this.iotConnected = Optional.ofNullable(request.isIotConnected()).orElse(null);
            this.batteryPercentage = Optional.ofNullable(request.getBatteryPercentage()).orElse(null);
            this.driveMode = Optional.ofNullable(request.getDriveMode()).orElse(null);
            this.createdAt = Optional.ofNullable(request.getCreatedAt()).orElse(null);
            this.updatedAt = Optional.ofNullable(request.getUpdatedAt()).orElse(null);
            this.imei = Optional.ofNullable(request.getImei()).orElse(null);
            this.ambientTemp = Optional.ofNullable(request.getAmbientTemp()).orElse(null);
            this.batteryCurrent = Optional.ofNullable(request.getBatteryCurrent()).orElse(null);
            this.batteryEfficiency = Optional.ofNullable(request.getBatteryEfficiency()).orElse(null);
            this.batteryHealth = Optional.ofNullable(request.getBatteryHealth()).orElse(null);
            this.batteryTemp = Optional.ofNullable(request.getBatteryTemp()).orElse(null);
            this.batteryUsage = Optional.ofNullable(request.getBatteryUsage()).orElse(null);
            this.batteryVitalScore = Optional.ofNullable(request.getBatteryVitalScore()).orElse(null);
            this.behaviorAcceleration = Optional.ofNullable(request.getBehaviorAcceleration()).orElse(null);
            this.behaviorBreaking = Optional.ofNullable(request.getBehaviorBreaking()).orElse(null);
            this.behaviorLastUpdated = Optional.ofNullable(request.getBehaviorLastUpdated()).orElse(null);
            this.behaviorOverspeed = Optional.ofNullable(request.getBehaviorOverspeed()).orElse(null);
            this.chargeCycles = Optional.ofNullable(request.getChargeCycles()).orElse(null);
            this.co2Saved = Optional.ofNullable(request.getCo2Saved()).orElse(null);
            this.controllerTemp = Optional.ofNullable(request.getControllerTemp()).orElse(null);
            this.distanceToEmpty = Optional.ofNullable(request.getDistanceToEmpty()).orElse(null);
            this.estResaleVal = Optional.ofNullable(request.getEstResaleVal()).orElse(null);
            this.fuelSaved = Optional.ofNullable(request.getFuelSaved()).orElse(null);
            this.lastSeenAt = Optional.ofNullable(request.getLastSeenAt()).orElse(null);
            this.latitude = Optional.ofNullable(request.getLatitude()).orElse(null);
            this.leaseStartReading = Optional.ofNullable(request.getLeaseStartReading()).orElse(null);
            this.locationLastUpdated = Optional.ofNullable(request.getLocationLastUpdated()).orElse(null);
            this.longitude = Optional.ofNullable(request.getLongitude()).orElse(null);
            this.motorTemp = Optional.ofNullable(request.getMotorTemp()).orElse(null);
            this.remainingBatteryCapacity = Optional.ofNullable(request.getRemainingBatteryCapacity()).orElse(null);
            this.resaleValComparison = Optional.ofNullable(request.getResaleValComparison()).orElse(null);
            this.totalBatteryCapacity = Optional.ofNullable(request.getTotalBatteryCapacity()).orElse(null);
            this.totalEnergy = Optional.ofNullable(request.getTotalEnergy()).orElse(null);
            this.vehicleCondition = Optional.ofNullable(request.getVehicleCondition()).orElse(null);
            this.vehicleEfficiency = Optional.ofNullable(request.getVehicleEfficiency()).orElse(null);
            this.charging = Optional.ofNullable(request.isCharging()).orElse(null);

            this.lastSpeed = Optional.ofNullable(request.getLastSpeed()).orElse(null);
            this.maxSpeed = Optional.ofNullable(request.getMaxSpeed()).orElse(null);
            this.stopsForTheDay = Optional.ofNullable(request.getStopsForTheDay()).orElse(null);
            this.totalOperationalHours = Optional.ofNullable(request.getTotalOperationalHours()).orElse(null);
            this.batteryVoltage = Optional.ofNullable(request.getBatteryVoltage()).orElse(null);
            this.distanceTravelledToday = Optional.ofNullable(request.getDistanceTravelledToday()).orElse(null);
            this.totalOdometer = Optional.ofNullable(request.getTotalOdometer()).orElse(null);
            this.vehicleId = Optional.ofNullable(request.getVehicleId()).orElse(null);
            this.dailyAvgSpeed = Optional.ofNullable(request.getDailyAvgSpeed()).orElse(null);
            this.dailySpeedCount = Optional.ofNullable(request.getDailySpeedCount()).orElse(null);
            this.monthlyRuntime = Optional.ofNullable(request.getMonthlyRuntime()).orElse(null);
            this.monthlyDistanceTravelled = Optional.ofNullable(request.getMonthlyDistanceTravelled()).orElse(null);
            this.latitudeDirection = Optional.ofNullable(request.getLatitudeDirection()).orElse(null);
            this.longitudeDirection = Optional.ofNullable(request.getLongitudeDirection()).orElse(null);
            this.dailyOdometerStart = Optional.ofNullable(request.getDailyOdometerStart()).orElse(null);
            this.additionalFields = Optional.ofNullable(request.getAdditionalFields()).orElse(null);
            this.averageBatteryTemp = Optional.ofNullable(request.getAverageBatteryTemp()).orElse(null);
            this.totalChargeCurrent = Optional.ofNullable(request.getTotalChargeCurrent()).orElse(null);
            this.totalChargeCurrentAtHighTemperature = Optional.ofNullable(request.getTotalChargeCurrentAtHighTemperature()).orElse(null);
            this.totalDischargeCurrent = Optional.ofNullable(request.getTotalDischargeCurrent()).orElse(null);
            this.totalDischargeCurrentAtHighTemperature = Optional.ofNullable(request.getTotalDischargeCurrentAtHighTemperature()).orElse(null);
        }
    }
}
