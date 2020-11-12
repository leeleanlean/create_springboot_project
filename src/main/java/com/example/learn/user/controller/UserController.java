package com.example.learn.user.controller;

import com.example.learn.user.entity.UserEntity;
import com.example.learn.user.service.UserService;
import com.example.learn.utils.ResultUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 查询所有数据
   * @param pageIndex
   * @param pageSize
   * @param userEntity
   * @return
   */
  @GetMapping("")
  public ResultUtil search(
    @RequestParam(value = "pageIndex", required = false, defaultValue = "0") String pageIndex,
    @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
    @RequestBody(required = false) UserEntity userEntity) {
    return userService.findAll(pageIndex, pageSize, userEntity);
  }

  /**
   * 查询一条数据
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResultUtil findById(@PathVariable Long id) {
    return userService.findById(id);
  }

  /**
   * 删除一条数据
   * @param id
   * @return
   */
  @DeleteMapping("/{id}")
  public ResultUtil delete(@PathVariable Long id) {
    System.out.println();
    return userService.delete(id);
  }

  /**
   * 新增一条数据
   * @param userEntity
   * @return
   */
  @PostMapping("")
  public ResultUtil create(@RequestBody UserEntity userEntity) {
    return userService.save(userEntity);
  }

  /**
   * 更新一条数据
   * @param userEntity
   * @return
   */
  @PutMapping("")
  public ResultUtil update(@RequestBody UserEntity userEntity) {
    return userService.save(userEntity);
  }
}
