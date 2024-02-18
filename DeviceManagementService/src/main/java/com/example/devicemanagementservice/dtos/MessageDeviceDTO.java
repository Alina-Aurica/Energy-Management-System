package com.example.devicemanagementservice.dtos;

import com.example.devicemanagementservice.entities.Status;
import com.google.gson.Gson;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class MessageDeviceDTO {
    private UUID id;
    private String name;
    private String description;
    private String address;
    private Float maximumHourlyEnergyConsumption;
    private UUID clientID;
    private Status status;

//    @Override
//    public String toString() {
//        Gson gson = new Gson();
//        return gson.toJson(this);
//    }
}
