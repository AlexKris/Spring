package com.spring.ioc.overview.repository;

import com.spring.ioc.overview.domain.Usr;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户信息仓库
 *
 * @author chris
 */
public class UsrRepository {

    private Collection<Usr> usrList; // 自定义 Bean

    private BeanFactory beanFactory; // 内建非 Bean 对象（依赖）

    private ObjectFactory<ApplicationContext> objectFactory;

    public Collection<Usr> getUsrList() {
        return usrList;
    }

    public void setUsrList(Collection<Usr> usrList) {
        this.usrList = usrList;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }
}
