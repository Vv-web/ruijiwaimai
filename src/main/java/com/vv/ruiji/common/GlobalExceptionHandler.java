package com.vv.ruiji.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author vv
 * @Version 1.0
 * 全局异常处理
 */

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        log.error(sqlIntegrityConstraintViolationException.getMessage());
        if (sqlIntegrityConstraintViolationException.getMessage().contains("Duplicate entry")){
            String[] split = sqlIntegrityConstraintViolationException.getMessage().split(" ");
            String msg = "用户" + split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("Shit!");
    }
}
