package com.lzh.repository;

import com.lzh.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lzh
 * @date 2018/12/21 22:43
 */
public interface UserRepository extends CrudRepository<User,Long> {

    User findByName(String userName);
}
