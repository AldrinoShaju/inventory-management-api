package com.familyShop.inventorymanagementapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Transaction {

    @Id
    private String txnId;
    private double quantity;
    private String unit;
    private String prodDate;
    private String expiryDate;
    private String costPrice;
    private String sellPrice;
    private String mrp;
    private Seller seller;
    private String timeStamp;
}
