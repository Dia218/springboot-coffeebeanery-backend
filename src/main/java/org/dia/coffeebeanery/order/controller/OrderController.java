package org.dia.coffeebeanery.order.controller;

import lombok.RequiredArgsConstructor;
import org.dia.coffeebeanery.order.constant.OrderState;
import org.dia.coffeebeanery.order.model.Order;
import org.dia.coffeebeanery.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;
    
    @GetMapping("/order/list") //구매자용 해당 이메일 주문 목록 페이지
    public Page<Order> getOrderListForBuyer(@RequestParam("email") String email,
                                            @RequestParam(value = "page", defaultValue = "0") int page) {
        return orderService.getOrderListByEmail(email, page);
    }
    
    @PostMapping //구매자용 주문 생성 (주문 결제 페이지)
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
    
    @PutMapping("/{orderId}") //구매자용 주문 수정 (주문 취소)
    public Order updateOrderCancel(@PathVariable Integer orderId) {
        return orderService.cancelOrder(orderId);
    }
    
    @GetMapping("/admin/orders") //판매자용 전체 주문 목록 페이지
    public Page<Order> getOrderListForSeller(@RequestParam(value = "page", defaultValue = "0") int page) {
        return this.orderService.getAllOrderList(page);
    }
    
    @PutMapping("/admin/orders/{orderId}") //판매자용 주문 수정 (주문 상태 및 배송 정보 수정)
    public Order updateOrderStatus(@PathVariable Integer orderId, @RequestBody OrderState newState) {
        
        //운송장 번호, 택배사 수정도..
        
        return orderService.updateOrderStatus(orderId, newState);
    }
    
    //주문은 삭제되지 않음
    //'주문 취소' 상태로 변경만 가능
}
