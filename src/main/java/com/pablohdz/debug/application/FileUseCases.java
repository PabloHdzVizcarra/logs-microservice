package com.pablohdz.debug.application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUseCases {
    public FileUseCases() {
    }

    public void createFileUseCase(String fileName, String message, String service) throws Exception {
        String pathString = String.format("src/main/resources/%s.txt", fileName);
        String textToWrite = String.format("%s: %s\n", service, message);
        createFileIfNotExists(pathString);
        try {
            Files.write(
                Paths.get(pathString),
                textToWrite.getBytes(),
                StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }

    private void createFileIfNotExists(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }
}
