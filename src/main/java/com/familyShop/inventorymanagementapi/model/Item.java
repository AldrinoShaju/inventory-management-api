package com.familyShop.inventorymanagementapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "items")
@ToString
public class Item {

    @Id
    private String itemId;
    private String name;
    private List<Transaction> transactionList;
}
