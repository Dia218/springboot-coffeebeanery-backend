package org.dia.coffeebeanery.order.service;

import lombok.RequiredArgsConstructor;
import org.dia.coffeebeanery.delivery.model.Delivery;
import org.dia.coffeebeanery.delivery.repository.DeliveryRepository;
import org.dia.coffeebeanery.exception.ResourceNotFoundException;
import org.dia.coffeebeanery.order.constant.OrderState;
import org.dia.coffeebeanery.order.model.Order;
import org.dia.coffeebeanery.order.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    
    public Page<Order> getOrderListByEmail(String email, int page) {
        Pageable pageable = PageRequest.of(page, 10);  // 페이징 처리, 한 페이지에 10개의 주문
        return orderRepository.findByCustomer_email(email, pageable);
    }
    
    public Order createOrder(Order order) {
        Delivery delivery = new Delivery();
        delivery.setDelivery_number(null); //운송장 번호 = 초기값 null
        delivery.setDelivery_company(null); //택배사 = 초기값 null
        delivery.setDelivery_address(order.getDelivery().getDelivery_address()); // 배송지 = 구매자가 입력한 주소
        
        delivery = deliveryRepository.save(delivery);
        order.setDelivery(delivery);
        
        return orderRepository.save(order);
    }
    
    public Order cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                                     .orElseThrow(() -> new ResourceNotFoundException("ERROR: " + orderId));
        
        order.setOrderState(OrderState.CANCELLED);
        return orderRepository.save(order);
    }
    
    public Order updateOrderStatus(Integer orderId, OrderState newState) {
        Order order = orderRepository.findById(orderId)
                                     .orElseThrow(() -> new ResourceNotFoundException("ERROR: " + orderId));
        
        order.setOrderState(newState);
        return orderRepository.save(order);
    }
    
    public Page<Order> getAllOrderList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.orderRepository.findAll(pageable);
    }
}
