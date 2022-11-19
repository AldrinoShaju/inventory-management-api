package com.familyShop.inventorymanagementapi.service;

import com.familyShop.inventorymanagementapi.model.Item;
import com.familyShop.inventorymanagementapi.model.Transaction;

import java.util.List;

public interface ItemService {

    public boolean addItem(String itemName);

    public List<Item> getAllItems();

    public boolean addItemTxn(String itemId, Transaction txn);

    public boolean updateItemTxnQuantity(String itemId, String txnId, double quantity);

    public boolean deleteItemTxn(String itemId, String txnId);
}
