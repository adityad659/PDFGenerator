package com.example.pdfgenerator.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice {

    @NotBlank(message = "id must not be blank")
    private String id;

    @NotBlank(message = "Seller name must not be blank")
    private String seller;

    @NotBlank(message = "Seller GSTIN must not be blank")
    private String sellerGstin;

    @NotBlank(message = "Seller address must not be blank")
    private String sellerAddress;

    @NotBlank(message = "Buyer name must not be blank")
    private String buyer;

    @NotBlank(message = "Buyer GSTIN must not be blank")
    private String buyerGstin;

    @NotBlank(message = "Buyer address must not be blank")
    private String buyerAddress;

    @NotNull(message = "Items must not be null")
    @Valid
    private List<Item> items;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerGstin() {
        return sellerGstin;
    }

    public void setSellerGstin(String sellerGstin) {
        this.sellerGstin = sellerGstin;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerGstin() {
        return buyerGstin;
    }

    public void setBuyerGstin(String buyerGstin) {
        this.buyerGstin = buyerGstin;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
