package com.vv.ruiji.controller;

import com.vv.ruiji.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * @author vv
 * @Version 1.0
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${ruiji.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //获取文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //UUID随机生成文件名称，并拼接后缀
        String filename = UUID.randomUUID().toString()+suffix;
        //创建目录对象
        File dir = new File(basePath);
        //判断是否存在当前目录
        if (!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(filename);
    }
}
