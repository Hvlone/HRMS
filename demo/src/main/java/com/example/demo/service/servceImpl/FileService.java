package com.example.demo.service.servceImpl;

import com.example.demo.config.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileProperties fileProperties;

    public List<String> saveImages(MultipartFile[] files) throws IOException {
        List<String> paths = new ArrayList<>();
        Path uploadPath = Paths.get(fileProperties.getUploadDir());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String original = file.getOriginalFilename();
                if (original == null || !original.contains(".")) continue;
                String ext = original.substring(original.lastIndexOf("."));
                String newName = UUID.randomUUID() + ext;
                Path target = uploadPath.resolve(newName);
                Files.copy(file.getInputStream(), target);
               /* paths.add("uploads/house-pics/" + newName);*/
                paths.add("house-pics/" + newName); // 相对路径
            }
        }
        return paths;
    }
}