package com.example.devicemanagementservice.controllers;

import com.example.devicemanagementservice.dtos.DeviceDTO;
import com.example.devicemanagementservice.entities.Device;
import com.example.devicemanagementservice.services.DeviceService;
import com.example.devicemanagementservice.services.implementation.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/findById/{id}")
    public ResponseEntity getDeviceById(@RequestHeader(value="Authorization", required=false) String token, @PathVariable UUID id){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidAndActive(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Optional<DeviceDTO> deviceDTO = deviceService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity getDeviceByName(@RequestHeader(value="Authorization", required=false) String token, @PathVariable String name){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidAndActive(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Optional<DeviceDTO> deviceDTO = deviceService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity getAllDevice(@RequestHeader(value = "Authorization", required = false) String token){
        System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        List<DeviceDTO> deviceDTO = deviceService.findAllDevice();
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @GetMapping("/findAllByName/{name}")
    public ResponseEntity getAllDeviceByName(@RequestHeader(value="Authorization", required=false) String token, @PathVariable String name){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        List<DeviceDTO> deviceDTO = deviceService.findAllDeviceByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @GetMapping("/findAllByAddress/{address}")
    public ResponseEntity getAllDeviceByAddress(@RequestHeader(value="Authorization", required=false) String token, @PathVariable String address){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        List<DeviceDTO> deviceDTO = deviceService.findAllDeviceByAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @GetMapping("/findAllByMaximumHourlyEnergyConsumption/{maximumHourlyEnergyConsumption}")
    public ResponseEntity getAllDeviceByMaximumHourlyEnergyConsumption(@RequestHeader(value="Authorization", required=false) String token, @PathVariable Float maximumHourlyEnergyConsumption){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        List<DeviceDTO> deviceDTO = deviceService.findAllDeviceByMaximumHourlyEnergyConsumption(maximumHourlyEnergyConsumption);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @GetMapping("/findAllByClientId/{clientID}")
    public ResponseEntity getAllDeviceByClientID(@RequestHeader(value="Authorization", required=false) String token, @PathVariable UUID clientID){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidAndActive(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        List<DeviceDTO> deviceDTO = deviceService.findAllDeviceByClientID(clientID);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestHeader(value="Authorization", required=false) String token, @RequestBody Device device){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Optional<DeviceDTO> deviceDTO = deviceService.insertDevice(device);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @PutMapping("/updateMaximumHourlyEnergyConsumption/{name}/{maximumHourlyEnergyConsumption}")
    public ResponseEntity updateDeviceMaximumHourlyEnergyConsumption(@RequestHeader(value="Authorization", required=false) String token, @PathVariable String name, @PathVariable Float maximumHourlyEnergyConsumption){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Optional<DeviceDTO> deviceDTO = deviceService.updateDeviceMaximumHourlyEnergyConsumption(name, maximumHourlyEnergyConsumption);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteDeviceByName(@RequestHeader(value="Authorization", required=false) String token, @PathVariable String name){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Optional<DeviceDTO> deviceDTO = deviceService.deleteDeviceByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @DeleteMapping("/deleteByClientID/{clientID}")
    public ResponseEntity deleteDeviceByClientID(@RequestHeader(value="Authorization", required=false) String token, @PathVariable UUID clientID){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidAndActive(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Optional<DeviceDTO> deviceDTO = deviceService.deleteDeviceByClientID(clientID);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

    @PutMapping("/addClientDevice/{name}/{clientID}")
    public ResponseEntity addClientDevice(@RequestHeader(value="Authorization", required=false) String token, @PathVariable String name, @PathVariable UUID clientID){
        //System.out.println("TOKEN: " + token);
        String goodToken = null;
        if(token != null)
            goodToken = jwtService.extractToken(token);
        if(!jwtService.isTokenValidByAdmin(goodToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        Optional<DeviceDTO> deviceDTO = deviceService.addClientID(name, clientID);
        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO);
    }

}
