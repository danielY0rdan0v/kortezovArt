package com.project.web.kortezovart.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Cloudinary cloudinary;


    public FileStorageServiceImpl( Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String saveFile(MultipartFile file) {
        try{

            var uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            return uploadResult.get("url").toString();
            }catch(Exception e){
            throw new RuntimeException("Грешка при качване на снимката в облака!", e);
        }

    }
}
