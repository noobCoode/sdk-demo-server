package com.ringcenter.sdkdemo.factory;

import com.ringcenter.sdkdemo.model.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Xu Dongdong
 * @date 2020-5-1
 */
public class ResponseEntityFactory {

    private ResponseEntityFactory() {
    }

    public static ResponseEntity<ResultBean> commonResult(ResultBean resultBean) {
        HttpStatus httpStatus = resultBean.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity(resultBean, httpStatus);
    }
}
