package org.dia.coffeebeanery.product.repository;

import org.dia.coffeebeanery.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
