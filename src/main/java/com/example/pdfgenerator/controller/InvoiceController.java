package com.example.pdfgenerator.controller;

import com.example.pdfgenerator.dto.request.Invoice;
import com.example.pdfgenerator.dto.response.GeneratePDFResponse;
import com.example.pdfgenerator.response.APIResponse;
import com.example.pdfgenerator.service.impl.InvoiceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @Operation(summary = "Generate Invoice PDF", description = "Creates a PDF invoice based on the provided Invoice object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Invoice PDF generated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GeneratePDFResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - PDF generation failed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)))
    })
    @PostMapping("/v1/generatepdf")
    public APIResponse generateInvoice(@RequestBody(required = true) Invoice invoice) {
        try {
            String fileName = invoiceServiceImpl.generatePdf(invoice);
            GeneratePDFResponse generatePDFResponse = new GeneratePDFResponse(fileName);
            return APIResponse.ok(generatePDFResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return APIResponse.error(1, "PDF Generation failed!");
        }
    }

    @GetMapping("/v1/downloadFile/{fileName}")
    @Operation(summary = "Download Invoice PDF", description = "Downloads the generated invoice PDF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Download File Link", content = @Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary"))),
            @ApiResponse(responseCode = "404", description = "Not Found - The requested file does not exist")
    })
    public ResponseEntity<Resource> downloadFile(@PathVariable @Schema(description = "Name of the invoice file to download (Ex: 1_invoice.pdf)") String fileName, HttpServletRequest request) {
        return invoiceServiceImpl.downloadFile(fileName, request);
    }
}
