package com.example.pdfgenerator.service.impl;

import com.example.pdfgenerator.exception.FileNotFoundException;
import com.example.pdfgenerator.service.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalStorage implements Storage {


    @Value("${storage.location.local}")
    private String storageLocation;

    @Override
    public String storeFile(byte[] fileContent, String fileName) throws IOException {
        Path filePath = Path.of(storageLocation).resolve(fileName).normalize();
        try (FileOutputStream fout = new FileOutputStream(filePath.toString())) {
            fout.write(fileContent);
        }
        return filePath.toString();
    }

    public boolean fileExists(String fileName) {
        Path filePath = Path.of(storageLocation).resolve(fileName).normalize();
        return Files.exists(filePath);
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Path.of(storageLocation).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }



}
