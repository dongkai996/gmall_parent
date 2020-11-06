package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import io.swagger.annotations.Api;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口")
@RestController
//@Controller
@RequestMapping("admin/product")
public class FileUploadController {

    //做回显使用的
    @Value("${fileServer.url}")
    private String fileUrl; //fileUrl = http://192.168.200.128:8080/

    //
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) throws Exception {

        //先读取到配置文件tracker.conf
        String configFile = this.getClass().getResource("/tracker.conf").getFile();
        String path = "";
        if(configFile!=null){
            ClientGlobal.init(configFile);
            //trackerClient 对象
            TrackerClient trackerClient = new TrackerClient();
            //获取trackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建storage
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,null);
            //上传文件
            //获取文件的后缀名
            String extName = FilenameUtils.getExtension(file.getOriginalFilename());
            path = storageClient1.upload_appender_file1(file.getBytes(), extName, null);

            System.out.println("文件路径：\t" + path);
        }

        //数据初始化
        //创建tracker
        //创建storage
        //执行上传

        return Result.ok(fileUrl + path);
    }
}
