package com.example.pdfgenerator.service;
import org.thymeleaf.context.Context;

public interface ITextPdfGenerator {

    byte[] generatePdf(Context context, String templateName);
}
