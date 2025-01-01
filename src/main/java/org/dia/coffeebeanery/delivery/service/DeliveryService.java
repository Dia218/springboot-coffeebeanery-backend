package org.dia.coffeebeanery.delivery.service;

import lombok.RequiredArgsConstructor;
import org.dia.coffeebeanery.delivery.model.Delivery;
import org.dia.coffeebeanery.delivery.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    
    public Delivery updateDelivery(Integer deliveryId, Delivery updatedDelivery) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryId)
                                                      .orElseThrow(() -> new IllegalArgumentException("ERROR: " + deliveryId));
        
        existingDelivery.setDelivery_number(updatedDelivery.getDelivery_number());
        existingDelivery.setDelivery_company(updatedDelivery.getDelivery_company());
        existingDelivery.setDelivery_address(updatedDelivery.getDelivery_address());
        
        return deliveryRepository.save(existingDelivery);
    }
}
