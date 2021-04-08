package com.spring.jpa.repository;

import com.spring.jpa.entity.Coffee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    @Query("select c from Coffee c where c.name = ?1")
    Coffee findCoffeeByNameCustomer(String name);

    @Query("select c from Coffee c order by c.id desc")
    Coffee findOrderByIdDescCustomer();

    @Query("select c from Coffee c where c.name like %?1")
    Coffee findCoffeeLikeNameCustomer(String name);

    @Query("select c from Coffee c where c.name like concat('%' ,?1 ,'%')")
    Coffee findCoffeeLikeNameCustomer2(String name);
}
