package com.example.learn.utils;

import java.util.Calendar;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
  private static String SIGN = "xxxxxx";

  /**
   * 生成token
   * @param map
   * @return
   */
  public static String getToken(Map<String, String> map) {
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.DATE, 1);

    JWTCreator.Builder builder = JWT.create();

    map.forEach((k, v) -> {
      builder.withClaim(k, v);
    });

    String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));

    return token;
  }

  /**
   * 验证token
   * @param token
   * @return
   */
  public static DecodedJWT verify(String token) {
    return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
  }
}
