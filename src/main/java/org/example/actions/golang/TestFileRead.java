package org.example.actions.golang;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestFileRead {
    public static void main(String[] args) {
        InputStream inputStream = TestFileRead.class.getClassLoader().getResourceAsStream("storage/golang/module_path.txt");

        if (inputStream == null) {
            System.out.println("File not found!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Print each line of the file
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
