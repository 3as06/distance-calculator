package com.Zas163.distCalc.util;

import com.Zas163.distCalc.entity.City;
import com.Zas163.distCalc.enums.CulcMode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.annotation.Generated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "from",
        "to",
        "mode"
})
@Generated("jsonschema2pojo")
public class DistanceRequirements {
    @JsonProperty("from")
    City from;
    @JsonProperty("to")
    City to;
    @JsonProperty("mode")
    CulcMode mode;
}



