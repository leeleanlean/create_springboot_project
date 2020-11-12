package com.example.learn.interceptors;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.learn.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.servlet.HandlerInterceptor;

public class JWTInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
  throws Exception {
      Map<String, Object> map = new HashMap<>();
      String token = request.getHeader("token");
      try {
        JWTUtil.verify(token);
        return true;
      } catch (SignatureVerificationException e) {
        e.printStackTrace();
        map.put("msg", "无效签名");
      } catch (TokenExpiredException e) {
        e.printStackTrace();
        map.put("msg", "token过期");
      } catch (AlgorithmMismatchException e) {
        e.printStackTrace();
        map.put("msg", "token有误");
      } catch (Exception e) {
        e.printStackTrace();
        map.put("msg", "token无效");
      }
      map.put("status", false);

      String json = new ObjectMapper().writeValueAsString(map);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().println(json);

      return false;
    }
}
