package com.example.pdfgenerator.utility;

public class FileUtils {

    public static String generateInvoiceFileName(String fileName, String extension) {
        return fileName + "_invoice" + extension;
    }
}
