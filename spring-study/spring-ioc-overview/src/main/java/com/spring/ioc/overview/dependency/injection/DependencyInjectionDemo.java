package com.spring.ioc.overview.dependency.injection;

import com.spring.ioc.overview.repository.UsrRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

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
 * Bean定义配置
 * <pre>
 *     1、基于 XML 文件
 *     2、基于 Properties 文件
 *     3、基于 Java 注解
 *     4、基于 Java API
 * </pre>
 * IoC容器配置
 * <pre>
 *     1、基于 XML 文件
 *     2、基于 Java 注解
 *     3、基于 Java API
 * </pre>
 * 外部化属性配置：基于 Java 注解
 *
 * @author chris
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // step 1 配置 XML 配置文件
        // step 2 启动 Spring 应用上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖注入 依赖来源一 自定义 Bean
        UsrRepository usrRepository = applicationContext.getBean("usrRepository", UsrRepository.class);

        System.out.println(usrRepository.getUsrList());

        // 依赖注入 依赖来源二 内建依赖
        System.out.println(usrRepository.getBeanFactory()); // DefaultListableBeanFactory
        System.out.println(usrRepository.getBeanFactory() == applicationContext); // false

        System.out.println(usrRepository.getObjectFactory().getObject()); // ClassPathXmlApplicationContext
        System.out.println(usrRepository.getObjectFactory().getObject() == applicationContext); // true

        // 依赖查找
//        System.out.println(beanFactory.getBean(BeanFactory.class)); // No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available

        // 依赖注入 依赖来源三 容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("environment = " + environment);

        whoIsIoCContainer(applicationContext, usrRepository);
    }

    // BeanFactory 和 ApplicationContext 哪个才是 Spring IoC 容器
    private static void whoIsIoCContainer(ApplicationContext applicationContext, UsrRepository usrRepository) {
        System.out.println(usrRepository.getBeanFactory()); // DefaultListableBeanFactory
        System.out.println(usrRepository.getBeanFactory() == applicationContext);

        // ApplicationContext is BeanFactory

        // ConfigurableApplicationContext extends ApplicationContext
        // ConfigurableApplicationContext#getBeanFactory()
    }
}
