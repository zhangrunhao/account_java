package com.zhangrh.account.javaserver.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zhangrh.account.javaserver.exception.Asserts;

/**
 * JwtTokenUtil
 */
public class JwtTokenUtil {

  private static String KEY_SECRET = "secret"; // 服务端密钥
  /**
   * 创建jwt token
   * @return
   */
  public static String generateToken(String email) {
    // jwt 头部
    Map<String, Object> map = new HashMap<>();
    map.put("alg", "HS236");
    map.put("type", "JWT");
    String token = null;

    try {
      token = JWT.create()
      .withHeader(map)
      .withIssuer("SERVICE") // 签发人
      .withClaim("email", email)
      .withIssuedAt(new Date()) // 签发时间
      .withExpiresAt(AddDate(null, 2*60)) // 过期时间
      .sign(Algorithm.HMAC512(KEY_SECRET)); // 签发密钥
    } catch (Exception e) {
      Asserts.fail("token 生成失败");
    }
    return token;
  }

  public static boolean verifyToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC512(KEY_SECRET);
      JWTVerifier verifier = JWT.require(algorithm)
        .withIssuer("SERVICE")
        .build();
      DecodedJWT jwt = verifier.verify(token);
      Map<String, Claim> claims = jwt.getClaims();
      if (claims == null) Asserts.fail("claims 为空");
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 计算时间
   * @param date
   * @param minus
   * @return
   */
  private static Date AddDate(Date date, Integer minus) {
    if (date == null) date = new Date();
    Calendar cal = new GregorianCalendar();
    cal.setTime(date);
    cal.add(Calendar.MINUTE, minus);
    return cal.getTime();
  }
}