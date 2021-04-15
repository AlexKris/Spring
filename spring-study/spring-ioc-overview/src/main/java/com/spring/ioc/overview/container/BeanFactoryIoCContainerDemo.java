package com.spring.ioc.overview.container;

import com.spring.ioc.overview.domain.Usr;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory} IoC 容器示例
 *
 * @author chris
 */
public class BeanFactoryIoCContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 配置 ClassPath 路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("beanDefinitionsCount = " + beanDefinitionsCount);
        // 依赖查找集合对象
        lookupByCollectionType(beanFactory);
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
