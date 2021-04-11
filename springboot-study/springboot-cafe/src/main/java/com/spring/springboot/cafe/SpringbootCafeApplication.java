package com.spring.springboot.cafe;

import com.spring.springboot.cafe.entity.Coffee;
import com.spring.springboot.cafe.entity.CoffeeOrder;
import com.spring.springboot.cafe.entity.OrderState;
import com.spring.springboot.cafe.repository.CoffeeRepository;
import com.spring.springboot.cafe.service.CoffeeOrderService;
import com.spring.springboot.cafe.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories
public class SpringbootCafeApplication implements ApplicationRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCafeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("All Coffee: {}", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = coffeeOrderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", coffeeOrderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", coffeeOrderService.updateState(order, OrderState.INIT));
        }
    }
}
