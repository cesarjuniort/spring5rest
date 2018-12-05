package com.cesarjuniort.springboot.apirest.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final static String UPLOAD_DIR = "uploads";
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	
	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path filePath = getPath(filename);
		log.info(filePath.toString());
		Resource res = new UrlResource(filePath.toUri());
		
		if(res != null && !res.exists() && !res.isReadable()) {
			throw new RuntimeException("Unable to load the image!");
		}
		return res;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String filename = UUID.randomUUID().toString()+"_"+ file.getOriginalFilename();
		Path filePath = getPath(filename);
		log.info(filePath.toString());
		Files.copy(file.getInputStream(), filePath);
		return filename; 
	}

	@Override
	public boolean delete(String filename) { 
		if(filename != null && filename.length() >0) {
			Path photoPath = getPath(filename);
			File deletePhotoFile = photoPath.toFile();
			if(deletePhotoFile.exists() && deletePhotoFile.canRead()) {
				return deletePhotoFile.delete();
			}
		}
		return false;
	}

	@Override
	public Path getPath(String filename) {		 
		return Paths.get(UPLOAD_DIR).resolve(filename).toAbsolutePath();
	}

}
