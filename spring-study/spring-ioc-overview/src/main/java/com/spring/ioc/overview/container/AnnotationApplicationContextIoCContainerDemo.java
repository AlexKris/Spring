package com.spring.ioc.overview.container;

import com.spring.ioc.overview.domain.Usr;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解能力 {@link ApplicationContext} IoC 容器示例
 *
 * @author chris
 */
public class AnnotationApplicationContextIoCContainerDemo {

    public static void main(String[] args) {
        // 创建 ApplicationContext 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextIoCContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextIoCContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupByCollectionType(applicationContext);
    }

    @Bean
    public Usr usr() {
        Usr usr = new Usr();
        usr.setId(1L);
        usr.setName("chris");
        return usr;
    }

    // 按照集合 Bean 类型查找
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Usr> usrMap = listableBeanFactory.getBeansOfType(Usr.class);
            System.out.println("usrMap by type = " + usrMap);
        }
    }
}
