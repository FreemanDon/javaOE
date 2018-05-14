package com.example.util;

import com.example.exceptions.CheckException;

import java.util.stream.Stream;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.util
 * @Description: TODO
 * @Creation Date:2018-05-10
 */
public class checkUtil {
    private static final String[] INVALID_NAMES = { "admin", "guanliyuan" };

    /**
     * 校验名字, 不成功抛出校验异常
     *
     * @param value
     */
    public static void checkName(String value) {
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
            throw new CheckException("name", value);
        });
    }
}
