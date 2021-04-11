package com.spring.springboot.mybatis.generator;

import com.spring.springboot.mybatis.generator.entity.Coffee;
import com.spring.springboot.mybatis.generator.entity.CoffeeExample;
import com.spring.springboot.mybatis.generator.mapper.CoffeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("com.spring.generator.mapper")
public class SpringbootOrmMybatisGeneratorApplication implements ApplicationRunner {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOrmMybatisGeneratorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        playWithArtifacts();
    }

    private void playWithArtifacts() {
        Coffee espresso = new Coffee()
                .withName("espresso")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(espresso);

        Coffee latte = new Coffee()
                .withName("latte")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(latte);

        Coffee s = coffeeMapper.selectByPrimaryKey(1L);
        log.info("Coffee {}", s);

        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo("latte");
        example.setOrderByClause("id desc");
        List<Coffee> list = coffeeMapper.selectByExample(example);
        list.forEach(e -> log.info("selectByExample: {}", e));
    }
}
