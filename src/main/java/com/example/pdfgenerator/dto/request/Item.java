package com.example.pdfgenerator.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @NotBlank(message = "Item name must not be blank")
    private String name;

    @NotBlank(message = "Quantity must not be blank")
    private String quantity;

    @NotNull(message = "Rate must not be null")
    private Double rate;

    @NotNull(message = "Amount must not be null")
    private Double amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
