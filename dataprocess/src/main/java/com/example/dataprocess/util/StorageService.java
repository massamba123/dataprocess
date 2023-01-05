package com.example.dataprocess.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final String pathFilename = "/home/massamba/Bureau/noflaye/dataprocess/src/main/resources/";

    public void uploadFile(MultipartFile file) throws IOException {

        if (file.isEmpty()){
              System.out.println("fichier null");
        }
        else {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(pathFilename + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
}