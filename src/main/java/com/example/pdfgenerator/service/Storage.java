package com.example.pdfgenerator.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface Storage {

    String storeFile(byte[] fileContent, String fileName) throws IOException;

    boolean fileExists(String fileName);

    Resource loadFileAsResource(String fileName);

}
