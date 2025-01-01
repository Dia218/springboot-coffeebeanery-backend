package org.dia.coffeebeanery.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.dia.coffeebeanery.delivery.model.Delivery;
import org.dia.coffeebeanery.delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeliveryController {
    private final DeliveryService deliveryService;
    
    @PutMapping("/{deliveryId}") //수정
    public Delivery updateProduct(@PathVariable Integer deliveryId, @RequestBody Delivery updatedDelivery) {
        return deliveryService.updateDelivery(deliveryId, updatedDelivery);
    }
}
