package com.spring.springboot.cache.redis.repository;

import com.spring.springboot.cache.redis.entity.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
