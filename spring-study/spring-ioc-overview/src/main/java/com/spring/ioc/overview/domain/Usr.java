package com.spring.ioc.overview.domain;

/**
 * 用户类
 *
 * @author chris
 */
public class Usr {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Usr{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
