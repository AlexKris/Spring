package com.spring.pagehelper;

import com.github.pagehelper.PageInfo;
import com.spring.pagehelper.entity.Coffee;
import com.spring.pagehelper.mapper.CoffeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("com.spring.pagehelper.mapper")
public class SpringOrmappingMybatisPagehelperApplication implements ApplicationRunner {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringOrmappingMybatisPagehelperApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 两页 每页3条记录
        coffeeMapper.findAllWitchRowBounds(new RowBounds(1, 3))
                .forEach(coffee -> log.info("Page(1) Coffee {}", coffee));
        coffeeMapper.findAllWitchRowBounds(new RowBounds(2, 3))
                .forEach(coffee -> log.info("Page(2) Coffee {}", coffee));

        log.info("=======================");

        // pageSize为0时，取出所有记录
        coffeeMapper.findAllWitchRowBounds(new RowBounds(1, 0))
                .forEach(coffee -> log.info("Page(1) Coffee {}", coffee));

        log.info("=======================");

        coffeeMapper.findAllWithParam(1, 3)
                .forEach(coffee -> log.info("Page(1) Coffee {}", coffee));
        List<Coffee> list = coffeeMapper.findAllWithParam(2, 3);
        PageInfo<Coffee> pageInfo = new PageInfo<>(list);
        log.info("PageInfo: {}", pageInfo);
    }
}
