package com.spring.springboot.jpa.repository;

import com.spring.springboot.jpa.entity.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
