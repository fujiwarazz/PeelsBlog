package com.peelsannaw.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * author:Peelsannaw
 * 图片上传到OSS工具类
 */

@Slf4j
@Data
public class UploadUtils {

    @Value("${oss.accessKey}")
    private String accessKey;
    @Value("${oss.secretKey}")
    private String secretKey;
    @Value("${oss.bucket}")
    private String bucket;

    private static String staticAccessKey;
    private static String staticSecretKey;
    private static String staticBucket;

    @PostConstruct
    public void getStatic(){
        staticAccessKey = this.accessKey;
        staticBucket=this.bucket;
        staticSecretKey = this.secretKey;
    }

    public static String uploadImg(MultipartFile img){
        log.info("access:{}",staticAccessKey);
        Configuration cfg = new Configuration(Region.huadongZheJiang2());
        UploadManager uploadManager = new UploadManager(cfg);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        String dataPath = sdf.format(new Date());
        //uuid为名字
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String fileType = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf('.'));
        String key = dataPath+uuid+fileType;
        try {
            InputStream fileInputStream = img.getInputStream();
            Auth auth = Auth.create(staticAccessKey, staticSecretKey);
            String upToken = auth.uploadToken(staticBucket);
            try {
                Response response = uploadManager.put(fileInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rfkb83lu8.bkt.clouddn.com/" +key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
