package com.example.devicemanagementservice;

import com.example.devicemanagementservice.entities.Device;
import com.example.devicemanagementservice.entities.Status;
import com.example.devicemanagementservice.repositories.DeviceRepository;
import com.example.devicemanagementservice.services.SendMessageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;


@SpringBootApplication
@Validated
public class DeviceManagementServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DeviceManagementServiceApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(DeviceManagementServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DeviceRepository deviceRepository, SendMessageService sendMessageService) {
        return args -> {
            Device device = new Device();
            device.setName("Apometru");
            device.setDescription("Masoara cantitatea de apa consumata");
            device.setAddress("Str. Ceahlau 77, Cluj-Napoca, Cluj");
            device.setMaximumHourlyEnergyConsumption(44.0F);
            Device deviceInserted = deviceRepository.save(device);
            sendMessageService.sendMessage(device, Status.ADD);

            Device device2 = new Device();
            device2.setName("Masina de spalat");
            device2.setDescription("Spala haine");
            device2.setAddress("Str. Ceahlau 77, Cluj-Napoca, Cluj");
            device2.setMaximumHourlyEnergyConsumption(78.9F);
            Device deviceInserted2 = deviceRepository.save(device2);

            System.out.println(deviceInserted);
            System.out.println(deviceInserted2);

        };


    }

}
