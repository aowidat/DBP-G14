package com.dpb.store.repos;

import com.dpb.store.entites.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
