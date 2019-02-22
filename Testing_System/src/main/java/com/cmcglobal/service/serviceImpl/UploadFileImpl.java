package com.cmcglobal.service.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
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

	@Override
	public String getPathFile(MultipartFile file) {
		File filePath = new File("files");
		String pathToSave = filePath.getAbsolutePath();
		System.out.println(pathToSave);

		File fileTranfer = new File(pathToSave + "/" + file.getOriginalFilename());
//		try {
//			file.transferTo(fileTranfer);
//		} catch (IllegalStateException e) {
//			return null;
//		} catch (IOException e) {
//			return null;
//		}
		System.out.println(file.getOriginalFilename());
		return fileTranfer.toString();
	}

	@Override
	public boolean checkExtension(MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if( !(("xlsx".equals(extension)) || ("xls".equals(extension))) ) {
			return false;
		}
		return true;
	}

}
