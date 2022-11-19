package com.familyShop.inventorymanagementapi.service;

import com.familyShop.inventorymanagementapi.model.Item;

import java.util.List;

public interface FeatureService {

    public List<Item> getExpireItems();
    public List<Item> getLowStockItems();

}
