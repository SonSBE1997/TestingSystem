package com.cmcglobal.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


public interface UploadFileService {
	String saveFile(MultipartFile file);
	String getPathFile(MultipartFile file);
	boolean checkExtension(MultipartFile file);
	public Resource loadFile(String filename);
}
