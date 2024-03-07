package com.advex10.upAndDownload.user;

import com.advex10.upAndDownload.dto.DownloadDTO;
import com.advex10.upAndDownload.files.FileManagementService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileManagementService fileManagementService;

    @PostMapping("/new")
    private UserEntity createNew (@RequestBody UserEntity user) {
        return userService.newUser(user);
    }

    @PostMapping("/profilePic/{id}")
    private UserEntity uploadProfilePic (@PathVariable Long id, @RequestParam MultipartFile pic) {
        return userService.uploadPic(id, pic);
    }

    @SneakyThrows
    @GetMapping("/profilePic/{id}")
    private @ResponseBody byte[] downloadProfilePic (@PathVariable Long id, HttpServletResponse response) {
        DownloadDTO dto = userService.downloadPic(id);

        String fileName = dto.getUser().getProfilePic();
        if(fileName == null) throw new Exception("User does not have any profile picture");

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
        return dto.getProfilePic();
    }

    @GetMapping("/get")
    private List <UserEntity> readAll () {
        return userService.getAll();
    }

    @GetMapping("/get/{id}")
    private Optional<UserEntity> readUser (@PathVariable Long id) {
        return userService.getOne(id);
    }

    @PatchMapping("/update/{id}")
    private UserEntity updateUser (@PathVariable Long id, @RequestParam UserEntity user) {
        return userService.updatedUser(id, user);
    }

    @DeleteMapping("/removeProfile/{id}")
    private void deleteProfandPics (@PathVariable Long id) {
        userService.userRemoval(id);
    }

    @DeleteMapping("/delete")
    private void deleteAll () {
        userService.deletedAll();
    }


}
