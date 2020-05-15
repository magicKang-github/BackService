package com.back.service.system.controller;

import com.back.service.common.base.AjaxResult;
import com.back.service.common.constant.ResultCode;
import com.back.service.common.utils.CosCloudUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;

/**
 * 上传文件至腾讯云存储对象
 * @author magicHat
 */
@RestController
@RequestMapping(value = "upload")
public class UploadFileController {

    @Value("${upload.user.file.images}")
    private String UserFileImage;

    @PostMapping(value = "file")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        AjaxResult ajaxResult;
        try{
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            String ContentType = file.getContentType();
            String url = CosCloudUtil.cosUploadInputStream(fileInputStream, ContentType, UserFileImage, suffix);
            ajaxResult = AjaxResult.success("成功！",url);
        }catch (Exception e){
            ajaxResult = AjaxResult.error(ResultCode.ERROR, e.getMessage());
        }
        return ajaxResult;
    }
}
