package server.alert.model;
import lombok.Data;

@Data
public class AlertMessage {
	 private long id;
	    private String alertType;
	    private String vehicleId;
	    private String message;
	    private String value;
	    private String status;
	    private String createdAt;
	    private String updatedAt;
}
