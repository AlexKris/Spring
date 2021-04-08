package com.spring.cafe.repository;

import com.spring.cafe.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
