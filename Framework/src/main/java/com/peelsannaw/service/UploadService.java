package com.peelsannaw.service;

import com.peelsannaw.common.Res;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    Res<?> uploadImg(MultipartFile img);
}
