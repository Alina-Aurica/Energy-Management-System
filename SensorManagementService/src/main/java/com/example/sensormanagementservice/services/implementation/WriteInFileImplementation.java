package com.example.sensormanagementservice.services.implementation;

import com.example.sensormanagementservice.services.WriteInFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class WriteInFileImplementation implements WriteInFile {
    private final String fileName = "src/main/resources/deviceIDConfig.txt";

    public WriteInFileImplementation() {
        //this.fileName = "D:/Facultate/ANUL 4/Anul 4 - Semestrul 1/SD/DS2023_30643_Aurica_Alina_Assignment_2/SensorManagementService/src/main/resources/deviceIDConfig.txt";
    }

    @Override
    public void writeInConfigFileWithAppend(UUID deviceID) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        String deviceIDString = String.valueOf(deviceID);
        writer.append(deviceIDString);
        writer.append('\n');

        writer.close();
    }

    @Override
    public void deleteFromConfigFile(UUID deviceID){
        String lineToRemove = String.valueOf(deviceID);

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
