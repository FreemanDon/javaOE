package com.example.routers;

import com.example.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.routers
 * @Description: TODO
 * @Creation Date:2018-05-10
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return RouterFunctions.nest(
                // 相当于类上面的 @RequestMapping("/user")
                RequestPredicates.path("/user"),
                // 下面的相当于类里面的 @RequestMapping
                // 得到所有用户
                RouterFunctions
                        .route(RequestPredicates.GET("/"),handler::getAllUser)
                        .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), handler::createUser)
                        .andRoute(RequestPredicates.DELETE("/del/{id}"), handler::deleteUserById));
    }
}
