package com.peelsannaw;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@SpringBootTest
//@ConfigurationProperties(prefix = "oss")
public class Oss {






    private String accessKey;
    private String secretKey;
    private String bucket;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Test
    public void test(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huadongZheJiang2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = "mmRaDHKa45U6Xirpsf2LbCVWQZjS3nYPU6_4bsEF";
//        String secretKey = "nP2SZDnMMMzpB-U_FBLWOKA43XgVkl7QUzHY0Gkr";
//        String bucket = "peelsannaw-blog";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "aqua";
        try {
           // byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
           // ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\27365\\Pictures\\aquaa.jpg");
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(fileInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
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
            //ignore
        }

    }

    @Test
    public void tes(){
        String img = "ccc.jpg";
        String fileType = img.substring(img.lastIndexOf('.'));

        System.out.println(fileType);
    }

    @Test
     public void test2(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String s = "123";
        String encode = passwordEncoder.encode(s);

        System.out.println(passwordEncoder.matches(s,encode));
    }
}
