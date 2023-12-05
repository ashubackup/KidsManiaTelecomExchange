package com.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserId {
    @JsonProperty("ID")
    private long ID;
    
    @JsonProperty("type")
    private int type;

}

