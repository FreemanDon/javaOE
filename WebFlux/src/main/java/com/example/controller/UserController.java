package com.example.controller;

import com.example.dao.UserDao;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.controller
 * @Description: Controller
 * @Creation Date:2018-05-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /* private final UserDao userDao;
     //使用构造函数注入
     //和spring的耦合度更加低
     public UserController(UserDao userDao) {
         this.userDao = userDao;
     }*/

    @Autowired
    private UserDao userDao;

    //flux写的时候用两种
    //1.一次性返回
    @GetMapping("/")
    public Flux<User> getAll() {
        return userDao.findAll();
    }

    //2.流返回
    //以sse形式多次返回数据
    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return userDao.findAll();
    }

    /**
     * 新增数据
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Mono<User> createUser(@Valid @RequestBody User user) {
            return userDao.save(user);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delUser(@PathVariable("id") String id) {
        return userDao.findById(id)
                //当要操作数据，并返回一个Mono的时候使用flatMap
                //如果不操作数据，只是转换数据，使用map
                .flatMap(user -> userDao.delete(user))
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable("id") String id,
                                                 @Valid @RequestBody User user) {
        return userDao.findById(id)
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return userDao.save(u);
                })
                //如果不操作数据，只是转换数据，使用map
                //此处是u->User
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> selectUser(@PathVariable("id") String id) {
        return userDao.findById(id)
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAge(@PathVariable("start") int start,
                                @PathVariable("end") int end) {
        return userDao.findByAgeBetween(start, end);
    }

    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(@PathVariable("start") int start,
                                      @PathVariable("end") int end) {
        return userDao.findByAgeBetween(start, end);
    }

    @GetMapping("/old")
    public Flux<User> oldUser() {
        return userDao.oldUser();
    }

    @GetMapping(value = "/stream/old", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamOldUser() {
        return userDao.oldUser();
    }
}
