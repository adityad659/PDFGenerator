package com.example.pdfgenerator.service.impl;

import com.example.pdfgenerator.service.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageFactory {

    @Autowired
    private LocalStorage localStorage;


    public Storage getStorage(String storageType) {
        if ("local".equalsIgnoreCase(storageType)) {
            return localStorage;
        }

        throw new IllegalArgumentException("Unknown storage type: " + storageType);
    }
}
