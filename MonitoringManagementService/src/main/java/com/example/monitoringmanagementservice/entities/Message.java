package com.example.monitoringmanagementservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 3L;

    private Timestamp timestamp;
    private UUID deviceID;
    private Float measurementValue;

}
