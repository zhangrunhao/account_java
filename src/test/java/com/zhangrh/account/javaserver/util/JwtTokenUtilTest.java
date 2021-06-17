package com.zhangrh.account.javaserver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zhangrh.account.javaserver.utils.JwtTokenUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTokenUtilTest {
  
  @Test
  void testGenerateAndParseAndVerityToken() {
    String email = "zhangrhweb@163.com";
    String token = JwtTokenUtil.generateToken(email);
    boolean flag = JwtTokenUtil.verifyToken(token);
    assertTrue(flag);
    
    String email1 = JwtTokenUtil.getJwtValue(token, "email");
    assertEquals(email, email1);
  }
}
