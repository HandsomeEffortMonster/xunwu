package com.lzh.service.user;

import com.lzh.entity.Role;
import com.lzh.entity.User;
import com.lzh.repository.RoleRepository;
import com.lzh.repository.UserRepository;
import com.lzh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2018/12/24 15:57
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findUserByName(String username) {
        User user = userRepository.findByName(username);

        if(user == null ){
            return null;
        }
        List<Role>  roles = roleRepository.findRolesByUserId(user.getId());
        if(roles == null || roles.isEmpty()){
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(
                "ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
