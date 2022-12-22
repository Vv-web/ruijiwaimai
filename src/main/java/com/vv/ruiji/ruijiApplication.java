package com.vv.ruiji;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author vv
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.vv.ruiji.dao")
@ServletComponentScan
public class ruijiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ruijiApplication.class,args);
        log.info("项目启动成功");
    }
}
