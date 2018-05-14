package com.example.handler;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.util.checkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.handler
 * @Description: TODO
 * @Creation Date:2018-05-10
 */

@Component
public class UserHandler {

    @Autowired
    private UserDao userDao;

    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON_UTF8)
                .body(userDao.findAll(), User.class);
    }

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {
        // 2.0.0 是可以工作, 但是2.0.1 下面这个模式是会报异常
        Mono<User> user = request.bodyToMono(User.class);

        return user.flatMap(u -> {
            // 校验代码需要放在这里
            checkUtil.checkName(u.getName());

            return ServerResponse.ok().contentType(APPLICATION_JSON_UTF8)
                    .body(userDao.save(u), User.class);
        });
    }

    /**
     * 根据id删除用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");

        return userDao.findById(id)
                .flatMap(
                        user -> userDao.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
