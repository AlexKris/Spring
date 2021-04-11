package com.spring.springboot.cache.redis.repository;

import com.spring.springboot.cache.redis.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
