package com.example.dao;

import com.example.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author DJ
 * @version V1.0
 * @Package: com.example.dao
 * @Description: dao
 * @Creation Date:2018-05-09
 */
@Repository
public interface UserDao extends ReactiveMongoRepository<User,String> {

    Flux<User> findByAgeBetween(int start, int end);

    @Query("{'age':{ '$gte': 20, '$lte' : 30}}")
    Flux<User> oldUser();
}
