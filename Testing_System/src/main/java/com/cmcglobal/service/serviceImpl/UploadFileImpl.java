package com.cmcglobal.service.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cmcglobal.service.UploadFileService;

@Service
public class UploadFileImpl implements UploadFileService {
	private final Path rootLocation = Paths.get("files");

	@Async
	@Override
	public String saveFile(MultipartFile file) {
		try {
			File files = new File(rootLocation + "/" + file.getOriginalFilename());
			if (files.exists())
				return "Success";
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
			return "Success";
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

}
