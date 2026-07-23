package com.workshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
