package com.example.monitoringmanagementservice.services.implementation;

import com.example.monitoringmanagementservice.entities.Device;
import com.example.monitoringmanagementservice.services.WriteFileService;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;



@Service
public class WriteFileServiceImplementation implements WriteFileService {

    private final String fileName;

    public WriteFileServiceImplementation() {
        this.fileName = "D:/Facultate/ANUL 4/Anul 4 - Semestrul 1/SD/DS2023_30643_Aurica_Alina_Assignment_2/SensorManagementService/deviceIDConfig.txt";
    }

    // mai am putin de studiat aici
    @Override
    public void writeInConfigFileWithAppend(Device device) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        String deviceIDString = String.valueOf(device.getId());
        writer.append(deviceIDString);
        writer.append('\n');

        writer.close();
    }

    @Override
    public void deleteFromConfigFile(Device device){
        String lineToRemove = String.valueOf(device.getId());

        try {
            File inputFile = new File(fileName);
            File tempFile = new File("tempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + "\n");
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Could not rename the temporary file!");
                }
            } else {
                System.out.println("Could not delete the original file!");
            }

            System.out.println("Line removed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
