package io.elice.shoppingmall.domain.orders.repository;

import io.elice.shoppingmall.domain.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
    List<Orders> findByUserId(Long userId);
}
