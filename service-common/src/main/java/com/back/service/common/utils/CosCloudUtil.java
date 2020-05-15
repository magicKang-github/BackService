package com.back.service.common.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.io.FileInputStream;

/**
 * 腾讯云对象存储工具类
 * @author magicHat
 */
public class CosCloudUtil {

    /**
     * 密钥 ID
     */
    private static String SECRET_ID = "AKIDNdBdu767qKnpncvf1FHGhyCHKGzTlZCg";
    /**
     * 密钥 Key
     */
    private static String SECRET_KEY = "SfdXN3CE3lGYeUDeGk2B7rUwgBUcU8Zo";
    /**
     * 存储桶名称
     */
    private static String BUCKET_NAME = "boc-1253721446";
    /**
     * 服务器来源
     */
    private static String REGION = "ap-beijing";

    /**
     * 上传文件
     * @param cosFile 需要上传的文件
     * @param urlPrefix 路径前缀
     * @param fileType 文件类型（后缀，例如.jpg、.png）
     * @return 文件访问地址
     */
    public static String cosUploadFile(File cosFile,String urlPrefix,String fileType){
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID,SECRET_KEY);
        ClientConfig clientConfig = new ClientConfig(new Region(REGION));
        COSClient cosClient = new COSClient(cred, clientConfig);
        String bucketName = BUCKET_NAME;
        String key = urlPrefix + System.currentTimeMillis() + fileType;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, cosFile);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        String url =cosClient.generatePresignedUrl(req).toString();
        return url.substring(0, url.indexOf("?"));
    }

    /**
     * 上传文件流的方式
     * @param cosInputStreamFile 文件输入流
     * @param contentType 文件传输类型
     * @param urlPrefix 路径前缀
     * @param fileType 文件类型（后缀，例如.jpg、.png）
     * @return 文件访问地址
     */
    public static String cosUploadInputStream(FileInputStream cosInputStreamFile, String contentType, String urlPrefix, String fileType){
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID,SECRET_KEY);
        ClientConfig clientConfig = new ClientConfig(new Region(REGION));
        COSClient cosClient = new COSClient(cred, clientConfig);
        String bucketName = BUCKET_NAME;
        String key = urlPrefix + System.currentTimeMillis() + fileType;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, cosInputStreamFile, objectMetadata);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        String url =cosClient.generatePresignedUrl(req).toString();
        return url.substring(0, url.indexOf("?"));
    }
}

