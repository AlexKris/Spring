package com.spring.springboot.cache.repository;

import com.spring.springboot.cache.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
