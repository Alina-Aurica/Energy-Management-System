package com.example.monitoringmanagementservice.controller;

import com.example.monitoringmanagementservice.dtos.SensorDTO;
import com.example.monitoringmanagementservice.services.SensorService;
import com.example.monitoringmanagementservice.services.implementation.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sensor")
public class SensorController {
    public SensorService sensorService;
    public JwtService jwtService;

    public SensorController(SensorService sensorService, JwtService jwtService) {
        this.sensorService = sensorService;
        this.jwtService = jwtService;
    }

    @GetMapping("/findAllByDeviceIDAndTimestamp/{deviceID}/{date}")
    public ResponseEntity getAllByDeviceIDAndTimestamp(@RequestHeader(value="Authorization", required=false) String token,@PathVariable UUID deviceID, @PathVariable Date date){
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByClient(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        List<SensorDTO> sensorsDTO = sensorService.findAllByDeviceIDAndTimestamp(deviceID, timestamp);
        return ResponseEntity.status(HttpStatus.OK).body(sensorsDTO);
    }
}
