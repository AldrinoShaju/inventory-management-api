package com.familyShop.inventorymanagementapi.controller;

import com.familyShop.inventorymanagementapi.model.Item;
import com.familyShop.inventorymanagementapi.model.Transaction;
import com.familyShop.inventorymanagementapi.service.ItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    ItemService service;

    @GetMapping("/inventory")
    public ResponseEntity getAllInventoryItems() {
        List<Item> allItems = service.getAllItems();
        return new ResponseEntity(allItems,HttpStatus.OK);
    }

    @PostMapping("inventory")
    public ResponseEntity addInventoryItem(@RequestParam String itemName) {
        boolean status = service.addItem(itemName);
        if(status) {
            return new ResponseEntity("Item "+itemName+" Added", HttpStatus.CREATED);
        }
        return new ResponseEntity("Item "+itemName+" failed to add", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("inventory/addTxn")
    public ResponseEntity addInventoryItemTxn(@RequestParam String itemId, @RequestBody Transaction txnRequest) {
        boolean status = service.addItemTxn(itemId, txnRequest);
        if(status){
            return new ResponseEntity("Txn Added to "+itemId, HttpStatus.CREATED);
        }
        return new ResponseEntity("Txn failed to Add to "+itemId, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("inventory")
    public ResponseEntity updateInventoryItem(@RequestParam String itemId, @RequestParam String txnId, @RequestParam double quantity) {
        boolean status = service.updateItemTxnQuantity(itemId, txnId, quantity);
        if(status){
            return new ResponseEntity("Updated item count", HttpStatus.CREATED);
        }
        return new ResponseEntity("failed to Updated count", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("inventory")
    public ResponseEntity deleteInventoryItemTxn(@RequestParam String itemId, @RequestParam String txnId) {
        boolean status = service.deleteItemTxn(itemId, txnId);
        if(status) {
            return new ResponseEntity("Item "+itemId+" txn "+txnId+" deleted", HttpStatus.OK);
        }
        return new ResponseEntity("item Txn failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
