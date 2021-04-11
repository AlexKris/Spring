package com.spring.ioc.overview.dependency.injection;

import com.spring.ioc.overview.repository.UsrRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入
 * <p>
 * 根据 Bean 名称注入
 * <p>
 * 根据 Bean 类型注入
 * <pre>
 *     1、单个 Bean 对象
 *     2、集合 Bean 对象
 * </pre>
 * 注入容器内建 Bean 对象
 * <p>
 * 注入非 Bean 对象
 * <p>
 * 注入类型
 * <pre>
 *     1、实时注入
 *     2、延时注入
 * </pre>
 *
 * @author chris
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // step 1 配置 XML 配置文件
        // step 2 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义 Bean
        UsrRepository usrRepository = beanFactory.getBean("usrRepository", UsrRepository.class);

        System.out.println(usrRepository.getUsrList());

        // 依赖注入
        System.out.println(usrRepository.getBeanFactory()); // DefaultListableBeanFactory
        System.out.println(usrRepository.getBeanFactory() == beanFactory); // false

        // 依赖查找
//        System.out.println(beanFactory.getBean(BeanFactory.class)); // No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available

        System.out.println(usrRepository.getObjectFactory().getObject()); // ClassPathXmlApplicationContext
        System.out.println(usrRepository.getObjectFactory().getObject() == beanFactory); // true
    }
}
