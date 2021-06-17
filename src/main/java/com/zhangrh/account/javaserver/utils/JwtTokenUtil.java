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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JwtTokenUtil
 */
public class JwtTokenUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

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

  /**
   * 验证jwt token
   * @param token
   * @return
   */
  public static boolean verifyToken(String token) {
    try {
      Map<String, Claim> claims = parseToken(token);
      if (claims == null) return false;
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 格式化token
   * @param token
   * @return
   */
  public static Map<String, Claim> parseToken(String token) {
    Map<String, Claim> claims = null;
    try {
      Algorithm algorithm = Algorithm.HMAC512(KEY_SECRET);
      JWTVerifier verifier = JWT.require(algorithm)
        .withIssuer("SERVICE")
        .build();
      DecodedJWT jwt = verifier.verify(token);
      claims = jwt.getClaims();
    } catch (Exception e) {
      LOGGER.warn("token 解析失败: " + e.getMessage());
      return null;
    }
    return claims;
  }

  /**
   * 获取token参数
   * @param token
   * @return
   */
  public static String getJwtValue(String token, String key) {
    Map<String, Claim> claims = parseToken(token);
    if (claims == null) return null;
    Claim claim = claims.get(key);
    return claim.asString();
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