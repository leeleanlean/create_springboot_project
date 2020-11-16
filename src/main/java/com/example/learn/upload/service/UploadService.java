package com.example.learn.upload.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.learn.utils.ResultUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UploadService {
  /**
   * 文件上传路径配置
   */
  @Value("${spring.servlet.multipart.location}")
  private String filePath;

  /**
   * 单文件上传
   * @param file
   * @return
   */
  public ResultUtil uploadFile(MultipartFile file) {
    if (file.isEmpty()) {
      return ResultUtil.error("文件参数<file>不能为空");
    }
    String fileName = file.getOriginalFilename();
    String fullPath = filePath + "/" + fileName;
    File upload = new File(fullPath);

    try {
      file.transferTo(upload);

      String msg = String.format("文件上传成功: %s", fullPath);
      log.info(msg);

      Map<String, String> map = new HashMap<>();
      map.put("message", "文件上传成功");
      map.put("filePath", fullPath);
      return ResultUtil.success().render(map);
    } catch (Exception e) {
      log.error(String.format("文件上传失败: %s", fullPath));
      return ResultUtil.error(e.getMessage());
    }
  }

  /**
   * 多文件上传
   * @param files
   * @return
   */
  public ResultUtil uploadFiles(List<MultipartFile> files) {
    List<String> list = new ArrayList<String>();

    // 未上传任何文件
    if (files.size() == 0) {
      return ResultUtil.error("文件参数<file>不能为空");
    }

    // 遍历上传文件
    for (int i = 0; i < files.size(); i++) {
      MultipartFile file = files.get(i);
      if (file.isEmpty()) {
        return ResultUtil.error("文件参数<file>不能为空");
      }

      String fileName = file.getOriginalFilename();
      String fullPath = filePath + "/" + fileName;
      File upload = new File(fullPath);
      try {
        file.transferTo(upload);
        list.add(fullPath);
        log.info(String.format("第%s个文件上传成功: %s", i + 1, fullPath));
      } catch (IOException e) {
        log.error(String.format("第%s个文件上传失败: %s", i + 1 ,fullPath));
        return ResultUtil.error(e.getMessage());
      }
    }
    return ResultUtil.success().render("list", list);
  }
}
