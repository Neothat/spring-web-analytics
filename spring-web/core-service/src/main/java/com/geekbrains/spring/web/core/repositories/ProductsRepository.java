package com.geekbrains.spring.web.core.repositories;

import com.geekbrains.spring.web.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select oi.product from OrderItem oi where oi.createdAt >= ?1 group by oi.product order by count(oi) desc")
    List<Product> getMostPurchasedProducts(LocalDateTime localDateTime);

    @Query("select oi.product from OrderItem oi where oi.createdAt >= ?1 group by oi.product order by oi.quantity desc")
    List<Product> getMostStackableProducts(LocalDateTime localDateTime);
}
