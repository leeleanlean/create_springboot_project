package com.example.learn.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class UserEntity {
  /**
   * 主键 id
   */
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  /**
   * 用户名
   */
  @Column(name = "name", unique = true, length = 32)
  private String name;

  /**
   * 年龄
   */
  @Column(name = "age", nullable = true, length = 2)
  private String age;

  /**
   * 创建时间
   */
  @CreatedDate
  @Column(name = "create_time", updatable = false,nullable = false)
  @org.hibernate.annotations.CreationTimestamp
  private Date createTime;

  /**
   * 更新时间
   */
  @LastModifiedDate
  @Column(name = "update_time", nullable = false)
  @org.hibernate.annotations.UpdateTimestamp
  private Date updateTime;
}
