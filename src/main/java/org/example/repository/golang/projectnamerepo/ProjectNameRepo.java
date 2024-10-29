package org.example.repository.golang.projectnamerepo;

import org.example.Main;
import org.example.constants.filepath.golang.FilePathConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProjectNameRepo {
    public static String getProjectName() {
        String path = null;
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(FilePathConstants.RESOURCE_PROJECT_NAME_PATH);

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((path = reader.readLine()) != null) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (path == null || path.isBlank()) {
            return null;
        }
        return path;
    }
}
