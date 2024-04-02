package com.example.pdfgenerator.service.impl;


import com.example.pdfgenerator.service.ITextPdfGenerator;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
public class ITextPdfGeneratorImpl implements ITextPdfGenerator {

        @Autowired
        private SpringTemplateEngine templateEngine;

        @Override
        public byte[] generatePdf(Context context, String templateName) {
            try {
                String htmlContent = dataToHtml(context, templateName);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PdfWriter pdfWriter = new PdfWriter(outputStream);
                ConverterProperties converterProperties = new ConverterProperties();
                DefaultFontProvider fontProvider = new DefaultFontProvider(false, true, false);
                converterProperties.setFontProvider(fontProvider);
                HtmlConverter.convertToPdf(htmlContent, pdfWriter, converterProperties);
                pdfWriter.close();
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private String dataToHtml(Context context, String templateName) {
            return templateEngine.process(templateName, context);
        }
}
