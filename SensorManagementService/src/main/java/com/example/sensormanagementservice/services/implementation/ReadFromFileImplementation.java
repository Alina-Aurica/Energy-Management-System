package com.example.sensormanagementservice.services.implementation;

import com.example.sensormanagementservice.dtos.MessageDTO;
import com.example.sensormanagementservice.entities.Message;
import com.example.sensormanagementservice.services.ReadFromFile;
import com.example.sensormanagementservice.services.publisher.RabbitMQJsonProducer;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
//@RequiredArgsConstructor
public class ReadFromFileImplementation implements ReadFromFile {
    ModelMapper modelMapper;
    RabbitMQJsonProducer rabbitMQJsonProducer;
    BufferedReader reader;
    InputStream in;
//    CSVReader reader;
    int i = 0;


    public ReadFromFileImplementation(ModelMapper modelMapper, RabbitMQJsonProducer rabbitMQJsonProducer) {
//        this.reader = new CSVReader(new FileReader("D:/Facultate/ANUL 4/Anul 4 - Semestrul 1/SD/DS2023_30643_Aurica_Alina_Assignment_2/SensorManagementService/src/main/resources/sensor.csv"));
        String fileNameCsv = "sensor.csv";
        this.in = getClass().getClassLoader().getResourceAsStream(fileNameCsv);
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.modelMapper = modelMapper;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    // Am identificat problema, nu stiu cum sa o rezolv
    @Override
    public List<UUID> readFromTextFileDeviceID() {
        // create a list of UUID values
        List<UUID> deviceIDs = new ArrayList<UUID>();
        String fileNameTxt = "src/main/resources/deviceIDConfig.txt";
        //String fileNameTxt = "deviceIDConfig.txt";
        //InputStream input = getClass().getClassLoader().getResourceAsStream(fileNameTxt);

        // read UUID values from file
        //try(BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileNameTxt))) {
            String line;
            while ((line = br.readLine()) != null) {
                // convert to UUID
                UUID deviceID = UUID.fromString(line);
                // add in list
                deviceIDs.add(deviceID);
                System.out.println("Device ID " + line);
            }
            return deviceIDs;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Scheduled(fixedRate = 5000)
    public void readFromCSVFile() {
        Message message = new Message();
        MessageDTO messageDTO;
        List<UUID> deviceIDs = readFromTextFileDeviceID();
        int sizeofList = deviceIDs.size();
        System.out.println("Lungime lista de deviceID-uri: " + sizeofList);

        // read from csv file
        try {
            String nextLine;
            if ((nextLine = reader.readLine()) != null) {
                // add value at measurementValuea
                message.setMeasurementValue(Float.valueOf(nextLine));
                System.out.println("Value we read " + nextLine);
                System.out.println("Time " + System.currentTimeMillis() + "\n");

                // set the deviceID
                //int randomValue = random.nextInt(sizeofList);
                int randomValue = i % sizeofList;
                message.setDeviceID(deviceIDs.get(randomValue));
                messageDTO = modelMapper.map(message, MessageDTO.class);

                rabbitMQJsonProducer.sendJsonMessage(messageDTO);
                i++;
                System.out.println("Message " + messageDTO.toString());
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
