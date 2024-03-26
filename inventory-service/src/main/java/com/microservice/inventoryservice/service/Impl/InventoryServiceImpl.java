package com.microservice.inventoryservice.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.repository.InventoryRepo;
import com.microservice.inventoryservice.service.InventoryService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepo.findBySkuCodeIn(skuCode).stream()
        .map(inventory -> 
            InventoryResponse.builder()
            .skuCode(inventory.getSkuCode())
            .isInStock(inventory.getQuantity()>0)
            .build()
        )
        .toList();
    }

}
