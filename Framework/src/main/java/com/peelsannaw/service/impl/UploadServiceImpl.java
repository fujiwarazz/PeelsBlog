package com.peelsannaw.service.impl;

import com.peelsannaw.common.Res;
import com.peelsannaw.enums.AppHttpCodeEnum;
import com.peelsannaw.exception.SystemException;
import com.peelsannaw.service.UploadService;
import com.peelsannaw.utils.UploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public Res<?> uploadImg(MultipartFile img) {
        //TODO:判断文件类型，文件大小,如果通过则上传到OSS
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        assert originalFilename != null;
        if(!originalFilename.endsWith(".png")&&!originalFilename.endsWith("jpg")&&!originalFilename.endsWith("JPG")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        String url = UploadUtils.uploadImg(img);
        System.out.println("------url"+url);
        return Res.okResult(url);
    }
}
