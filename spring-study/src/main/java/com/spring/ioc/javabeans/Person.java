package com.spring.ioc.javabeans;

/**
 * 描述人的 POJO 类
 * Setter / Getter 方法
 * 可写方法(Writable) / 可读方法(Readable)
 */
public class Person {

    // Property
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
