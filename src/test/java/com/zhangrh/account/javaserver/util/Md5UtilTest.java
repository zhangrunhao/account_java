package com.zhangrh.account.javaserver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zhangrh.account.javaserver.utils.Md5Util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Md5UtilTest {
  
  @Test
  void test() {
    String md5 = Md5Util.getMd5("zhang.22");
    assertEquals("e336d7fe804cf615dad396e1910f4160", md5);
  }
}
