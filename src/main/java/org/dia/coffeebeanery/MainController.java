package org.dia.coffeebeanery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/") //메인 페이지
    public String root() {
        return "redirect:/product/list";
    }
    
    
    /*To do list*/
    
    //구매 시 재고 검사
    //구매 시 재고 갱신
    //주문 시 주문 상세 목록
    //주문 상태 수정 및 택배사/운송장 정보 수정
}