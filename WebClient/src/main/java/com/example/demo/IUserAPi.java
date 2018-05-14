package com.example.demo;

import com.example.ApiService;
import com.example.domain.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.demo
 * @Description: webFlux两个服务做的一个接口
 * @Creation Date:2018-05-11
 */

@ApiService("http://localhost:8080/user")
public interface IUserAPi {


    @GetMapping("/")
    Flux<User> getAllUser();

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    @DeleteMapping("/{id}")
    Mono<Void> deleteUserById(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);
}
