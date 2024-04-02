package com.example.pdfgenerator.service;

import com.example.pdfgenerator.dto.request.Invoice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface InvoiceService {

    String generatePdf(Invoice invoice) throws IOException;

    ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request);

}
