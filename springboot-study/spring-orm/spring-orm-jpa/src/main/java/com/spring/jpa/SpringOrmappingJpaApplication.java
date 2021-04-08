package com.spring.jpa;

import com.spring.jpa.entity.Coffee;
import com.spring.jpa.entity.CoffeeOrder;
import com.spring.jpa.repository.CoffeeOrderRepository;
import com.spring.jpa.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class SpringOrmappingJpaApplication implements CommandLineRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringOrmappingJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initOrders();
    }

    private void initOrders() {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("Coffee: {}", espresso);

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);

        log.info("findCoffeeByNameCustomer: {}", coffeeRepository.findCoffeeByNameCustomer("espresso"));
        log.info("findOrderByIdDescCustomer: {}", coffeeRepository.findOrderByIdDescCustomer());
        log.info("findCoffeeLikeNameCustomer: {}", coffeeRepository.findCoffeeLikeNameCustomer("lat"));
        log.info("findCoffeeLikeNameCustomer2: {}", coffeeRepository.findCoffeeLikeNameCustomer2("at"));

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Collections.singletonList(espresso))
                .state(0)
                .build();
        coffeeOrderRepository.save(order);
        log.info("Order: {}", order);

        order = CoffeeOrder.builder()
                .customer("Han Mei")
                .items(Arrays.asList(espresso, latte))
                .state(0)
                .build();
        coffeeOrderRepository.save(order);
        log.info("Order: {}", order);
    }
}
