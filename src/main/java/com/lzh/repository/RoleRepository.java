package com.lzh.repository;

import com.lzh.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色数据DAO
 * @author lzh
 * @date 2018/12/24 16:13
 */
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> findRolesByUserId(Long userId);

}
