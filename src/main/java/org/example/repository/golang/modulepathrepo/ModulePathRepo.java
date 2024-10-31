package org.example.repository.golang.modulepathrepo;

import org.example.Main;
import org.example.constants.filepath.golang.FilePathConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ModulePathRepo {
    public static String getModulePath() {
        String path = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(FilePathConstants.MODULE_PATH));
            if (!lines.isEmpty()) {
                path = lines.get(0); // Get the first line
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (path == null || path.isBlank()) {
            return null;
        }
        return path;
    }
}
