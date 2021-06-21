package com.pablohdz.debug.application;

import java.io.File;
import java.io.IOException;

public class FileUseCases {
    public FileUseCases() {
    }

    public void createFileUseCase(String fileName) throws Exception {
        createFileIfNotExists(fileName);
    }

    private void createFileIfNotExists(String fileName) throws IOException {
        String path = String.format("src/main/resources/%s.txt", fileName);
        File file = new File(path);
        file.createNewFile();
    }
}
