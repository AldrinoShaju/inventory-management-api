package com.familyShop.inventorymanagementapi.repository;

import com.familyShop.inventorymanagementapi.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRespository extends MongoRepository<Item, String> {
}
