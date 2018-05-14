package com.example.proxys;

import com.example.interfaces.ProxyCreator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.proxys
 * @Description: 使用jdk动态代理实现代理类
 * @Creation Date:2018-05-11
 */
@Slf4j
public class JDKProxyCreator  implements ProxyCreator {
    @Override
    public Object createProxy(Class<?> type) {
        return null;
    }
}
