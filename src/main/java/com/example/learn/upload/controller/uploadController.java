package com.example.learn.upload.controller;

import java.util.List;

import com.example.learn.upload.service.UploadService;
import com.example.learn.utils.ResultUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("upload")
public class uploadController {
  @Autowired
  private UploadService uploadService;

  /**
   * 单文件上传
   * @param file
   * @return
   */
  @PostMapping("/file")
  public ResultUtil uploadFile(MultipartFile file) {
    return uploadService.uploadFile(file);
  }

  /**
   * 多文件上传
   * @param request
   * @return
   */
  @PostMapping("/files")
  public ResultUtil uploadFiles(MultipartHttpServletRequest request) {
    List<MultipartFile> files = request.getFiles("file");
    return uploadService.uploadFiles(files);
  }
}
