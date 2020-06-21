package com.alpha.cainiaoshop.product.excpection;

import com.alpha.common.utils.R;
import com.alpha.common.exception.BizcodeEnume;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice(basePackages = "com.alpha.cainiaoshop.product" )
public class cainiaoshopControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现异常", e.getMessage(), e.getCause());
        HashMap<String, String> errormap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((item) -> {
            errormap.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(BizcodeEnume.VAILD_EXCEPTION.getCode(), BizcodeEnume.VAILD_EXCEPTION.getMsg()).put("data", errormap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        System.out.println(throwable);
        return R.error();
    }
}
