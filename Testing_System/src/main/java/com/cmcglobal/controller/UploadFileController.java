package com.cmcglobal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.cmcglobal.service.UploadFileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class UploadFileController {

	@Autowired
	private UploadFileService uploadFileService;

	List<String> files = new ArrayList<>();

	@PostMapping(value="/uploadfile")
	public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile file) throws IOException {
		
		if( !(uploadFileService.checkExtension(file)) ) {
			String mess = "Not matching extension file";
			return ResponseEntity.status(HttpStatus.OK).body(mess);
		}
		String message = "";
		try {
			uploadFileService.saveFile(file);
			files.add(file.getOriginalFilename());
			message = "successfully uploaded" + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body("OK");
		} catch (Exception e) {
			message = "ERROR! can't upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(message);
		}
	}
	
	@GetMapping("/getallfiles")
	public ResponseEntity<Set<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadFileController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());
		Set<String> listSet = new HashSet<>(fileNames);
		return ResponseEntity.ok().body(listSet);
	}
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = uploadFileService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
