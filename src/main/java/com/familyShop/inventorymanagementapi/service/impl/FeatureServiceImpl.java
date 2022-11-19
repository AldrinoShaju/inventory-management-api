package com.familyShop.inventorymanagementapi.service.impl;

import com.familyShop.inventorymanagementapi.model.Item;
import com.familyShop.inventorymanagementapi.repository.ItemRespository;
import com.familyShop.inventorymanagementapi.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    ItemRespository respository;

    @Override
    public List<Item> getExpireItems() {
        List<Item> allItems = respository.findAll();

        allItems.stream().forEach(item -> {
            item.setTransactionList(item.getTransactionList().stream().filter(txn->{
               if(txn.getQuantity()>0) {
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                   LocalDate prodDate = LocalDate.parse(txn.getProdDate(), formatter);
                   LocalDate expiryDate = LocalDate.parse(txn.getExpiryDate(), formatter);

                   Period period = Period.between(prodDate, expiryDate);
                   if(period.getMonths()<=1) {
                       return true;
                   }
               }
                return false;
            }).collect(Collectors.toList()));
        });

        return allItems;
    }

    @Override
    public List<Item> getLowStockItems() {
        List<Item> allItems = respository.findAll();

        allItems.stream().forEach(item -> {
            item.setTransactionList(item.getTransactionList().stream().filter(txn->{
                if(txn.getQuantity()<5) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList()));
        });

        return allItems;
    }
}
