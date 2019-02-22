package com.cmcglobal.service;

import org.springframework.web.multipart.MultipartFile;


public interface UploadFileService {
	public String saveFile(MultipartFile file);
}
