package org.dia.coffeebeanery.product.controller;

import lombok.RequiredArgsConstructor;
import org.dia.coffeebeanery.product.model.Product;
import org.dia.coffeebeanery.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductController {
    
    private final ProductService productService;
    
    @GetMapping({"/product/list", "/admin/products"}) //구매자용 상품 목록 페이지, 판매자용 상품 목록 페이지
    public Page<Product> getProductList(@RequestParam(value = "page", defaultValue = "0") int page) {
        return this.productService.getAllProductList(page);
    }
    
    @PostMapping("/admin/products") //판매자용 상품 생성
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    
    @PutMapping("/admin/products/{productId}") //판매자용 상품 수정
    public Product updateProduct(@PathVariable Integer productId, @RequestBody Product updatedProduct) {
        return productService.updateProduct(productId, updatedProduct);
    }
    
    @DeleteMapping("/admin/products/{productId}") //판매자용 상품 삭제
    public void deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
    }
    
}
