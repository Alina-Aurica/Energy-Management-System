package com.example.sensormanagementservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;


@AllArgsConstructor
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 4L;

    private Timestamp timestamp;
    private UUID deviceID;
    private Float measurementValue;

    public Message(){
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
