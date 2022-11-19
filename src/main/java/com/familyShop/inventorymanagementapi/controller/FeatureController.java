package com.familyShop.inventorymanagementapi.controller;

import com.familyShop.inventorymanagementapi.model.Item;
import com.familyShop.inventorymanagementapi.service.FeatureService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeatureController {

    @Autowired
    FeatureService service;

    @GetMapping("expire-items")
    public ResponseEntity getExpireItems() {
        List<Item> expireItems = service.getExpireItems();
        return new ResponseEntity(expireItems, HttpStatus.OK);
    }

    @GetMapping("low-stock-items")
    public ResponseEntity getLowStockItems() {
        List<Item> lowStockItems = service.getLowStockItems();
        return new ResponseEntity(lowStockItems, HttpStatus.OK);
    }
}
