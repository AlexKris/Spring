package com.spring.ioc.overview.dependency.lookup;

import com.spring.ioc.overview.annotation.Vip;
import com.spring.ioc.overview.domain.Usr;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 * <p>
 * 根据 Bean 名称查找
 * <pre>
 *     1、实时查找
 *     2、延时查找
 * </pre>
 * 根据 Bean 类型查找
 * <pre>
 *     1、单个 Bean 对象
 *     2、集合 Bean 对象
 * </pre>
 * 根据 Bean 名称 + 类型查找
 * <p>
 * 根据 Java 注解查找
 * <pre>
 *     1、单个 Bean 对象
 *     2、多个 Bean 对象
 * </pre>
 *
 * @author chris
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        // step 1 配置 XML 配置文件
        // step 2 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
//        lookupInRealTime(beanFactory);
//        lookupInLazy(beanFactory);
        lookupBySingleType(beanFactory);
        lookupByCollectionType(beanFactory);
        lookupByAnnotation(beanFactory);
    }

    // 实时查找
    private static void lookupInRealTime(BeanFactory beanFactory) {
        Usr usr = (Usr) beanFactory.getBean("usr");
        System.out.println("usr real time = " + usr);
    }

    // 延时查找
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<Usr> objectFactory = (ObjectFactory<Usr>) beanFactory.getBean("objectFactory");
        Usr usr = objectFactory.getObject();
        System.out.println("usr lazy = " + usr);
    }

    // 按照单个 Bean 类型查找
    private static void lookupBySingleType(BeanFactory beanFactory) {
        // Spring 3.0 支持 Java 5 泛型
        Usr usr = beanFactory.getBean(Usr.class);
        System.out.println("usr by type real time = " + usr);
    }

    // 按照集合 Bean 类型查找
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Usr> usrMap = listableBeanFactory.getBeansOfType(Usr.class);
            System.out.println("usrMap by type = " + usrMap);
        }
    }

    // 根据 Java 注解查找
    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Usr> usrMap = (Map) listableBeanFactory.getBeansWithAnnotation(Vip.class);
            System.out.println("usrMap by annotation = " + usrMap);
        }
    }
}
