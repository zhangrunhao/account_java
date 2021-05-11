package com.zhangrh.account.javaserver.service;

import java.util.Map;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.DefinitionException;
import com.zhangrh.account.javaserver.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Autowired
  PasswordEncoder passwordEncoder;
  
  // public User getUserById(long id) {
    //   return userMapper.getById(id);
    // }

  public Map<String, Object> signin(String email, String password) {
    User user = userMapper.getUserByEmail(email);
    if (user != null && user.getPassword().equals(password)) {
      return Map.of("User", user);
    }
    return Map.of("error", "SIGNIN_FAILED");
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userMapper.getUserByEmail(email);
    if (user != null) {
      return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .passwordEncoder(s -> passwordEncoder.encode(user.getPassword()))
        .authorities(new SimpleGrantedAuthority("user"))
        .build();
    }
    throw new DefinitionException(400, "Don't query this email");
  }
}
