package com.lzh.service;

import com.lzh.entity.User;

/**
 * 用户服务
 * @author lzh
 * @date 2018/12/24 11:28
 */
public interface IUserService {
    User findUserByName(String username);
}
