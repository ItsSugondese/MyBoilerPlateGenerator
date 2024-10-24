package org.example.repository.modulepathrepo.golang;

import org.example.Main;
import org.example.constants.filepath.golang.FilePathConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ModulePathRepo {
    public static String getModulePath() {
        String path = null;
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(FilePathConstants.RESOURCE_MODULE_PATH);

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
