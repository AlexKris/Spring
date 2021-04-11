package com.spring.springboot.cafe.repository;

import com.spring.springboot.cafe.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
