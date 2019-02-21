package com.cmcglobal.service.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.scheduling.annotation.Async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cmcglobal.service.UploadFileService;

@Service
public class UploadFileImpl implements UploadFileService {
	private final Path rootLocation = Paths.get("files");

	@Autowired
	private ServletContext servletContext;

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
	public String saveFileVer(MultipartFile file, String pathSave) {
		String pathToSave = servletContext.getRealPath(pathSave);
		File fileTranfer = new File(pathToSave + "/" + file.getOriginalFilename());
		try {
			// transfer the received file to the given destination file
			file.transferTo(fileTranfer);
		} catch (IllegalStateException e) {
		} catch (IOException e) {
		}
		return file.getOriginalFilename();
	}

	
	@Override
	public Resource findFileByName(String nameFile) {
		// TODO Auto-generated method stub
		try {
			Path filePath = this.rootLocation.resolve(nameFile).normalize();
			Resource resource = (Resource) new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new Exception();
			}
		} catch (Exception ex) {
			return null;
		}
	}

}
