package com.cmcglobal.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
  String saveFile(MultipartFile file);

  String getPathFile(MultipartFile file);

  boolean checkExtension(MultipartFile file);

  public Resource loadFile(String filename);
}
