package server.alert.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class AlertDeserializer extends JsonDeserializer<Alert> {
    @Override
    public Alert deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        Alert alert = mapper.readValue(p, Alert.class);

        switch (alert.getAlertType()) {
            case "InsuranceRenewal":
                alert.setAdditionalFields(mapper.readValue(p, InsuranceRenewalFields.class));
                break;
            case "VehicleOffline":
                alert.setAdditionalFields(mapper.readValue(p, VehicleOfflineFields.class));
                break;
            case "DeepDischarge":
                alert.setAdditionalFields(mapper.readValue(p, DeepDischargeFields.class));
                break;
            case "LowCharge":
                alert.setAdditionalFields(mapper.readValue(p, LowChargeFields.class));
                break;
            default:
                // Handle unknown alert types or set a default
        }
        return alert;
    }
}
