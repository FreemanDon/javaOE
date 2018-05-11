package com.example.interfaces;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.interfaces
 * @Description: 创建代理类接口
 * @Creation Date:2018-05-11
 */
public interface ProxyCreator {
    /**
     *创建代理类
     * WebClientApplication 中 输入一个代理，返回一个对象
     */
    Object createProxy(Class<?> type);
}
