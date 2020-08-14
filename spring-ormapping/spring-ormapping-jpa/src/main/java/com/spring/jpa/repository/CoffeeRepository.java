package com.spring.jpa.repository;

import com.spring.jpa.entity.Coffee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    @Query("select c from Coffee c where c.name = ?1")
    Coffee getCoffeeByCoffeeName(String name);
}
