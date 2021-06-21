package com.pablohdz.debug.application;

import com.pablohdz.debug.domain.ErrorLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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

    public boolean checkIfExistsFile(String filename) throws IOException {
        String pathString = String.format("src/main/resources/%s.txt", filename);
        File file = new File(pathString);
        if (file.exists()) {
            return file.exists();
        } else {
            throw new IOException("the " + filename + " file is not exists");
        }
    }

    private void createFileIfNotExists(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }

    public List<ErrorLog> getErrorsFromFile(String filename) {
        List<ErrorLog> errorLogList = new ArrayList<>();
        String pathString = String.format("src/main/resources/%s.txt", filename);
        File file = new File(pathString);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String st;
            while ((st = bufferedReader.readLine()) != null) {
                String[] data = st.split(":");
                ErrorLog errorLog = new ErrorLog(data[0].trim(), data[1].trim());
                errorLogList.add(errorLog);
            }
            return errorLogList;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return errorLogList;
    }
}
