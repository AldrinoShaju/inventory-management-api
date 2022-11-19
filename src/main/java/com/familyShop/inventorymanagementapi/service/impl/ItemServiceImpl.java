package com.familyShop.inventorymanagementapi.service.impl;

import com.familyShop.inventorymanagementapi.model.Item;
import com.familyShop.inventorymanagementapi.model.Transaction;
import com.familyShop.inventorymanagementapi.repository.ItemRespository;
import com.familyShop.inventorymanagementapi.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRespository respository;

    @Override
    public boolean addItem(String itemName) {
        Item item = new Item();
        item.setName(itemName);
        item.setTransactionList(new ArrayList<Transaction>());
        try {
            respository.save(item);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> allItems = respository.findAll();
        return allItems;
    }

    @Override
    public boolean addItemTxn(String itemId, Transaction txn) {
        Optional<Item> item = respository.findById(itemId);
        if(item.isPresent()) {
            log.info("Fetched Item data: "+item.get().toString());
            ObjectId id = new ObjectId();
            txn.setTxnId(String.valueOf(id));
            txn.setTimeStamp(String.valueOf(System.currentTimeMillis()));
            item.get().getTransactionList().add(txn);
            try {
                respository.save(item.get());
            }catch (Exception e){
                log.error(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean updateItemTxnQuantity(String itemId, String txnId, double quantity) {
        Optional<Item> item = respository.findById(itemId);
        if(item.isPresent()) {
            List<Transaction> transactionList = item.get().getTransactionList();
            transactionList.stream().forEach(txn->{
                if(txn.getTxnId().equals(txnId)) {
                    txn.setQuantity(txn.getQuantity() - quantity); //TODO: Negative quantity case needs to be handled
                }
            });

            item.get().setTransactionList(transactionList);

            try{
                respository.save(item.get());
            }catch (Exception e) {
                log.error(e.getMessage());
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItemTxn(String itemId, String txnId) {
        Optional<Item> item = respository.findById(itemId);
        if(item.isPresent()) {
            item.get().setTransactionList(item.get().getTransactionList()
                    .stream().filter(txn->!(txn.getTxnId().equals(txnId))).collect(Collectors.toList()));

            try {
                respository.save(item.get());
            }catch (Exception e) {
                log.error(e.getMessage());
                return false;
            }

            return true;
        }
        return false;
    }
}
