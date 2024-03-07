package com.advex10.upAndDownload.files;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileManagementService {
//    @Value("${user.dir}")
    public String filePath;

    public String upload (MultipartFile multipartFile) throws IOException {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String newName = UUID.randomUUID() + "." + extension;

        File folder = new File(filePath);
        if (!folder.exists()) throw new IOException("This folder does not exist!");
        if (!folder.isDirectory()) throw new IOException("This is not a directory!");

        File here = new File( folder + "/" + newName);
        if(here.exists()) throw new IOException("Name conflict");

        multipartFile.transferTo(here);
        return newName;
    }

    @SneakyThrows
    public byte [] download (String fileName) {
        File file = new File(filePath + "/" + fileName);
        if(!file.exists()) throw new Exception("The file does not exist");
        return IOUtils.toByteArray(new FileInputStream(file));
    }

    @SneakyThrows
    public void removePic(String fileName) {
        File file = new File(filePath + "/" + fileName);
        if(!file.exists()) return;
        boolean deleteRes = file.delete();
        if(deleteRes == false) throw new Exception("Cannot delete file!");
    }
}
