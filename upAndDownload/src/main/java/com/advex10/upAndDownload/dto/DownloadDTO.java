package com.advex10.upAndDownload.dto;

import com.advex10.upAndDownload.user.UserEntity;
import lombok.Data;

@Data
public class DownloadDTO {
    private UserEntity user;
    private byte[] profilePic;
}
