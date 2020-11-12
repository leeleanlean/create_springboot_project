package com.example.learn.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.learn.utils.JWTUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  /**
   * 登录
   * @param user
   * @param request
   * @return
   */
  @PostMapping("/login")
  public Map<String, Object> login (
    @RequestBody Map<String, String> user,
    HttpServletRequest request
  ) {
    Map<String, Object> map = new HashMap<>();

    String userName = user.get("userName");
    String password = user.get("password");
    if (userName.equals("lean") && password.equals("111111")) {
      String token = JWTUtil.getToken(user);

      map.put("status", true);
      map.put("msg", "登录成功");
      map.put("token", token);
      return map;
    } else {
      map.put("status", false);
      map.put("msg", "登录失败");
      return map;
    }
  }

  /**
   * 注册
   * @return
   */
  @PostMapping("/register")
  public String register () {
    return "register";
  }
}
