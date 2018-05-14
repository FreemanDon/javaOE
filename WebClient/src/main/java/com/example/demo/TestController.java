package com.example.demo;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.demo
 * @Description: TODO
 * @Creation Date:2018-05-11
 */
@RestController
public class TestController {

    @Autowired
    IUserAPi userAPi;

    @GetMapping("/")
    public void test() {
        //直接调用 实现调用rest接口的效果
        Flux<User> users = userAPi.getAllUser();
        users.subscribe(System.out::println);
    }
}
