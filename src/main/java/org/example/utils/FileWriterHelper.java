package org.example.utils;

import org.example.Main;

import java.io.*;

public class FileWriterHelper {
    public static void readAndWriteFromStorageFileToFile(String storagePath, String filePath, String module) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(storagePath);

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.replace("?l", StringUtils.toCamelCase(module, '-', false));
                    line = line.replace("?u", StringUtils.toCamelCase(module, '-', true));
                    line = line.replace("?same", module);
                    line = line.replace("?mne", module.replace("-", "_").toUpperCase());
                    line = line.replace("?snake", module.replace("-", "_"));

                    String swagName = module.replace("-", " ");
                    line = line.replace("?swag", Character.toString(swagName.charAt(0)).toUpperCase() + swagName.substring(1));
                    writer.write(line);
                    writer.newLine(); // Write a newline after each line
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
