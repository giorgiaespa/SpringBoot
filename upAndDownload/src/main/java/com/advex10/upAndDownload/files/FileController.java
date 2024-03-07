package com.advex10.upAndDownload.files;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileManagementService fileManagementService;

    @PostMapping("/upload")
    private List<String> insertPic (@RequestBody MultipartFile[] multipartFiles) throws IOException {
        List<String>files = new ArrayList<>();
        for (MultipartFile file: multipartFiles){
            String uploadedFile = fileManagementService.upload(file);
            files.add(uploadedFile);
        }
        return files;
    }

    @GetMapping("/download")
    private byte[] savePic (@RequestParam String fileName, HttpServletResponse response) {
        System.out.println("Downloading " + fileName);

        String extension = FilenameUtils.getExtension(fileName);
        switch(extension){
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
            case "jpeg":
            case "jpg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
        }

        response.setHeader("Content disposition", "attachment; fileName=/" + fileName + "/");

        return fileManagementService.download(fileName);
    }
}
