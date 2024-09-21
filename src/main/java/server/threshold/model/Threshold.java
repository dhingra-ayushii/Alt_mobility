package server.threshold.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Threshold {
    private ThresholdParameter thresholdParameter;
    private String thresholdValue;
    private Condition condition;
}
