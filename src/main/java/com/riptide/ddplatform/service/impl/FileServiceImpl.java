package com.riptide.ddplatform.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.exception.GlobalException;
import com.riptide.ddplatform.service.FileService;
import com.riptide.ddplatform.util.PathUtils;
import com.riptide.ddplatform.util.ResultGenerator;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class FileServiceImpl implements FileService {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;


    // 上传文件到七牛云
    private String uploadOss(MultipartFile imgFile, String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {

                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
//                return "http://rsaaqbr24.bkt.clouddn.com/"+key;

                return key;
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
        return null;
    }

    /**
     * 获取下载文件的链接
     *
     * @param fileName 文件名称
     * @return 下载文件的链接
     */
    public String getFileUrl(String fileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        String finalUrl = String.format("%s/%s", "http://" + domain, encodedFileName);
        System.out.println(finalUrl);
        return finalUrl;
    }

    @Override
    public APIResult upload(MultipartFile file) {
        //判断文件类型
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //如果判断通过上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(file,filePath);
        return ResultGenerator.genSuccess(ApiEnum.FILE_UPLOAD_SUCCESS, url);
    }

    @Override
    public APIResult download(String file_name) {
        try {
            String fileUrl = getFileUrl(file_name);
            return ResultGenerator.genSuccess(ApiEnum.SUCCESS,fileUrl);
        } catch (UnsupportedEncodingException e) {
            throw new GlobalException(ApiEnum.FAILED);
        }
    }

}
