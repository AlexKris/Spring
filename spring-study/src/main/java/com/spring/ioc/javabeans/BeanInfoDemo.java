package com.spring.ioc.javabeans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * {@link java.beans.BeanInfo} 示例
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo1 = Introspector.getBeanInfo(Person.class);
        Stream.of(beanInfo1.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println("propertyDescriptor 1 = " + propertyDescriptor);
        });
        /*
        getBeanInfo(beanClass,stopClass)
        stopClass 追溯方法截至的类
         */
        BeanInfo beanInfo2 = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo2.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println("propertyDescriptor 2 = " + propertyDescriptor);
            // PropertyDescriptor 允许添加属性编辑器 -> PropertyEditor
            // 例如：将text(String) -> PropertyType
            // name -> String
            // age -> Integer
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            if ("age".equals(propertyDescriptor.getName())) {
                // 为 age 属性增加 PropertyEditor
                // String -> Integer
                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
            }
        });
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            super.setValue(value);
        }
    }
}
