package com.eshop.shopnow.security;

import com.eshop.shopnow.mapper.AdminMapper;
import com.eshop.shopnow.mapper.UserMapper;
import com.eshop.shopnow.models.Admins;
import com.eshop.shopnow.models.Users;
import com.eshop.shopnow.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.lang.model.type.NullType;
import java.util.Collections;

@Component
public class UserAuthentication implements AuthenticationProvider {
    @Autowired AdminMapper adminMapper;
    @Autowired UserMapper userMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String user = authentication.getName();
        String password = authentication.getCredentials().toString();

        Admins admins = adminMapper.findByUsername(user);
        Users users = userMapper.findByUsername(user);
        try{
            admins.getUsername();

            if(admins.getUsername() != null && user.equals(admins.getUsername())){
                return new UsernamePasswordAuthenticationToken(user, password, Collections.emptyList());
            }
        } catch (NullPointerException e){
            try {String uname = users.getUsername();
                if(user.equals(uname) ){

                    String salt = users.getSalt();
                    HashService hashService = new HashService();
                    String hashPassword = hashService.getHashedValue(password, salt);

                    if(hashPassword.equals(users.getPassword())){
                        return new UsernamePasswordAuthenticationToken(user, password, Collections.emptyList());
                    }
                }
            }catch(NullPointerException ex){throw new BadCredentialsException("External system authentication failed");}


        }

        throw new BadCredentialsException("External system authentication failed");

    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
