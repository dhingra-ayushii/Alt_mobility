package server.threshold.model;

public enum ThresholdParameter {

    IOT_CONNECTED("iot_connected"),
    BATTERY_PERCENTAGE("battery_percentage"),
    DRIVE_MODE("drive_mode"),
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at"),
    IMEI("imei"),
    AMBIENT_TEMP("ambient_temp"),
    BATTERY_CURRENT("battery_current"),
    BATTERY_EFFICIENCY("battery_efficiency"),
    BATTERY_HEALTH("battery_health"),
    BATTERY_TEMP("battery_temp"),
    BATTERY_USAGE("battery_usage"),
    BATTERY_VITAL_SCORE("battery_vital_score"),
    BEHAVIOR_ACCELERATION("behavior_accelaration"),
    BEHAVIOR_BREAKING("behavior_breaking"),
    BEHAVIOR_LAST_UPDATED("behavior_last_updated"),
    BEHAVIOR_OVERSPEED("behavior_overspeed"),
    CHARGE_CYCLES("charge_cycles"),
    CO2_SAVED("co2_saved"),
    CONTROLLER_TEMP("controller_temp"),
    DISTANCE_TO_EMPTY("distance_to_empty"),
    EST_RESALE_VAL("est_resale_val"),
    FUEL_SAVED("fuel_saved"),
    LAST_SEEN_AT("last_seen_at"),
    LATITUDE("latitude"),
    LEASE_START_READING("lease_start_reading"),
    LOCATION_LAST_UPDATED("location_last_updated"),
    LONGITUDE("longitude"),
    MOTOR_TEMP("motor_temp"),
    REMAINING_BATTERY_CAPACITY("remaining_battery_capacity"),
    RESALE_VAL_COMPARISON("resale_val_comparison"),
    TOTAL_BATTERY_CAPACITY("total_battery_capacity"),
    TOTAL_ENERGY("total_energy"),
    VEHICLE_CONDITION("vehicle_condition"),
    VEHICLE_EFFICIENCY("vehicle_efficiency"),
    CHARGING("charging"),
    LAST_SPEED("last_speed"),
    MAX_SPEED("max_speed"),
    STOPS_FOR_THE_DAY("stops_for_the_day"),
    TOTAL_OPERATIONAL_HOURS("total_operational_hours"),
    BATTERY_VOLTAGE("battery_voltage"),
    DISTANCE_TRAVELLED_TODAY("distance_travelled_today"),
    TOTAL_ODOMETER("total_odometer"),
    VEHICLE_ID("vehicle_id"),
    DAILY_AVG_SPEED("daily_avg_speed"),
    DAILY_SPEED_COUNT("daily_speed_count"),
    MONTHLY_RUNTIME("monthly_runtime"),
    MONTHLY_DISTANCE_TRAVELLED("monthly_distance_travelled"),
    LATITUDE_DIRECTION("latitude_direction"),
    LONGITUDE_DIRECTION("longitude_direction"),
    DAILY_ODOMETER_START("daily_odometer_start"),
    ADDITIONAL_FIELDS("additional_fields"),
    AVERAGE_BATTERY_TEMP("averageBatteryTemp"),
    TOTAL_CHARGE_CURRENT("totalChargeCurrent"),
    TOTAL_CHARGE_CURRENT_AT_HIGH_TEMP("totalChargeCurrentAtHighTemprature"),
    TOTAL_DISCHARGE_CURRENT("totalDischargeCurrent"),
    TOTAL_DISCHARGE_CURRENT_AT_HIGH_TEMP("totalDischargeCurrentAtHighTemprature");

    private final String parameterName;

    ThresholdParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
