package com.lzh.Security;

import com.lzh.entity.User;
import com.lzh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义认证实现
 * @author lzh
 * @date 2018/12/24 11:26
 */
public class AuthProvider implements AuthenticationProvider{
    @Autowired
    private IUserService userService;

    private final Md5PasswordEncoder passwordEncoder =  new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String)authentication.getCredentials();

        User user = userService.findUserByName(userName);
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        //md5加密需要md5 encode加密器
        if(this.passwordEncoder.isPasswordValid(user.getPassword(),inputPassword,user.getId())){
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        throw new BadCredentialsException("autherror");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;//支持所有认证类
    }
}
