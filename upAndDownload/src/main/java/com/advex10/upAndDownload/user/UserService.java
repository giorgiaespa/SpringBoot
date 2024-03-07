package com.advex10.upAndDownload.user;

import com.advex10.upAndDownload.dto.DownloadDTO;
import com.advex10.upAndDownload.files.FileManagementService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileManagementService fileManagementService;

    public UserEntity newUser(UserEntity user) {
        return userRepository.saveAndFlush(user);
    }

    @SneakyThrows
    public UserEntity uploadPic(Long id, MultipartFile pic) {
        Optional<UserEntity>user = userRepository.findById(id);
        if(!user.isPresent()) throw new Exception("User not found!");
        String profilePic = fileManagementService.upload(pic);
        UserEntity user1 = user.get();
        if(user1.getProfilePic() != null){
            fileManagementService.removePic(user1.getProfilePic());
        };
        user1.setProfilePic(profilePic);
        return userRepository.save(user1);
    }

    @SneakyThrows
    public DownloadDTO downloadPic(Long id){
        Optional<UserEntity>user = userRepository.findById(id);
        DownloadDTO dto = new DownloadDTO();

        dto.setUser(user.get());

        String pic = user.get().getProfilePic();
        if(pic == null) return dto;
        byte [] picInBytes = fileManagementService.download(pic);
        dto.setProfilePic(picInBytes);

        return dto;
    }

    public List<UserEntity> getAll () {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getOne (Long id) {
        return userRepository.findById(id);
    }

    public UserEntity updatedUser (Long id, UserEntity user){
        if (userRepository.existsById(id)){
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    public void deletedUser (Long id) {
        userRepository.deleteById(id);
    }

    public void deletedAll () {
        userRepository.deleteAll();
    }

    @SneakyThrows
    public void userRemoval(Long id) {
        Optional<UserEntity>user = userRepository.findById(id);
        String pic = user.get().getProfilePic();
        if(pic != null){
            fileManagementService.removePic(pic);
        }
        userRepository.deleteById(id);
    }
}
