package com.ringcenter.sdkdemo.model;

import com.ringcenter.sdkdemo.enums.ErrorEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author Xu Dongdong
 * @date 2020-4-30
 */
@Data
@Builder
public class ResultBean<T> {
    private int bzCode;
    private boolean isSuccess;
    private T result;
    private String msg;
    private String traceId;

    /**
     * new success
     *
     * @param result
     * @return
     */
    public static <T> ResultBean newSuccess(T result) {
        return builder()
                .bzCode(0)
                .result(result)
                .msg(null)
                .traceId(null)
                .isSuccess(true)
                .build();
    }

    /**
     * new fail
     *
     * @param errorEnum
     * @param traceId
     * @return
     */
    public static ResultBean newFail(ErrorEnum errorEnum, String traceId) {
        return builder()
                .bzCode(errorEnum.getBzCode())
                .result(null)
                .msg(errorEnum.getMsg())
                .traceId(traceId)
                .isSuccess(false)
                .build();
    }

}
