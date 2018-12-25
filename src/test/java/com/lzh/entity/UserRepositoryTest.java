package com.lzh.entity;

import com.lzh.ApplicationTests;
import com.lzh.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lzh
 * @date 2018/12/21 22:45
 */
public class UserRepositoryTest extends ApplicationTests{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){
        User user = userRepository.findOne(1L);
        Assert.assertEquals("wali",user.getName());
    }
}
