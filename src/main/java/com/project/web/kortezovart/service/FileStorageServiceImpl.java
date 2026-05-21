package com.project.web.kortezovart.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path root = Paths.get("uploads");

    public FileStorageServiceImpl() {
        try {
            Files.createDirectories(root);
        }catch (IOException e){
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String saveFile(MultipartFile file) {
        try{
            if(file.isEmpty()){
                throw new RuntimeException("Failed to store empty file!");
            }

            String fileName = UUID.randomUUID().toString()+ "_" + file.getOriginalFilename();

            Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/"+fileName;
        }catch (IOException  e){
            throw new RuntimeException("Failed to store file!",e);
        }
    }
}
