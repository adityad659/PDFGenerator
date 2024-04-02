package com.example.pdfgenerator.service.impl;

import com.example.pdfgenerator.dto.request.Invoice;
import com.example.pdfgenerator.service.ITextPdfGenerator;
import com.example.pdfgenerator.service.InvoiceService;
import com.example.pdfgenerator.service.Storage;
import com.example.pdfgenerator.service.impl.StorageFactory;
import com.example.pdfgenerator.utility.FileUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.IOException;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private StorageFactory storageFactory;

    @Autowired
    private ITextPdfGenerator iTextPdfGenerator;

    public String generatePdf(Invoice invoice) throws IOException {
        String fileName = FileUtils.generateInvoiceFileName(invoice.getId(), ".pdf");
        Storage storage =  storageFactory.getStorage("local");
        if(storage.fileExists(fileName)) {
            return fileName;
        }
        Context context = new Context();
        context.setVariable("invoice", invoice);
        byte[] pdfData = iTextPdfGenerator.generatePdf(context, "invoice");
        storage.storeFile(pdfData, fileName);
        return fileName;
    }

    public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request) {
        Storage storage =  storageFactory.getStorage("local");
        Resource resource = storage.loadFileAsResource(fileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
