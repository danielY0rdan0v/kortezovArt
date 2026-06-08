package com.project.web.kortezovart.service;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.upload-dir:./uploads}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file) {

        if(file == null || file.isEmpty()){
           return null;
        }
        try {
            // Създаваме папката uploads, ако случайно не съществува
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Генерираме уникално име, за да не се дублират снимки с едно и също име
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(uniqueFileName);

            // Физическо запазване на файла
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Връщаме пътя, който ще се запази в базата данни
            return "/uploads/" + uniqueFileName;

        } catch (Exception e) {
            throw new RuntimeException("Грешка при запазване на файла локално!", e);
        }

    }

    @Override
    public void deleteFile(String fileUrl) {

        if (fileUrl == null || fileUrl.isEmpty()) {
            return;
        }

        try {
            // Премахваме "/uploads/" от текста, за да вземем само чистото име на файла
            String fileName = fileUrl.replace("/uploads/", "");
            Path filePath = Paths.get(uploadDir).resolve(fileName);

            // Физическо изтриване от диска
            Files.deleteIfExists(filePath);

        } catch (IOException e) {
            System.err.println("Грешка при триене на файл от диска: " + e.getMessage());
        }
    }
}
