package com.spring.jdbc.dao;

import com.spring.jdbc.entity.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData() {
        Arrays.asList("b", "c").forEach(bar -> jdbcTemplate.update("insert  into foo (bar) values (?)", bar));

        HashMap<String, String> row = new HashMap<>();
        row.put("bar", "id");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("id of d: {}", id.longValue());
    }

    public void listData() {
        log.info("Count: {}", jdbcTemplate.queryForObject("select count(*) from foo", Long.class));

        List<String> list = jdbcTemplate.queryForList("select bar from foo", String.class);
        list.forEach(s -> log.info("Bar: {}", s));

        List<Foo> fooList = jdbcTemplate.query("select * from foo",
                (rs, rowNum) -> Foo.builder().id(rs.getLong(1)).bar(rs.getString(2)).build());
        fooList.forEach(f -> log.info("Foo: {}", f));
    }
}
