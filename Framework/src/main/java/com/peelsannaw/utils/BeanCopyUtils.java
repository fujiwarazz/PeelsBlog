package com.peelsannaw.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;


public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    /**
     * @Author Peelsannaw
     * @param source
     * @param clazz 由于之前的写法需要循环 然后手动创建一个需要复制到的对象，十分麻烦，所以这里直接使用class对象
     * @param <V> 确定返回值对象
     * <v>代表定义的泛型，第二个标识返回的类型
     */
    public static <T> T copyBean(Object source, Class<? extends T> clazz) {
        //创建目标对象,这一步省略了强制转化
        T result = null;
        try {
            //通过反射的无参构造获取一个目标的对象
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}