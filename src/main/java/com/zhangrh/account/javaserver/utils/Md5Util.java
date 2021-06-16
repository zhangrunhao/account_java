package com.zhangrh.account.javaserver.utils;

import org.springframework.util.DigestUtils;

public class Md5Util {

  public static String getMd5(String str) {
    return DigestUtils.md5DigestAsHex(str.getBytes());
  }
}
