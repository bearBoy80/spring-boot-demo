

package com.github.bearboy.spring.common.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author:bearBoy80
 * @date: 2020/4/15
 * @sina 1.0
 * @Description: 通用响应
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Result<T> {
    private int code;

    private String message;

    private T data;

    private boolean sucess;

    public static Result sucess() {
        return new Result().setCode(ResultCode.SUCCESS.code()).setMessage(ResultCode.SUCCESS.name()).setSucess(true);
    }

    public static Result sucess(String message) {
        return new Result().setCode(ResultCode.SUCCESS.code()).setMessage(message).setSucess(true);
    }

    public static <T> Result<T> data(T data) {
        return new Result().setCode(ResultCode.SUCCESS.code()).setMessage(ResultCode.SUCCESS.name()).setData(data).setSucess(true);
    }

    public static Result fail(String message) {
        return new Result().setCode(ResultCode.FAIL.code()).setMessage(message).setSucess(false);
    }
}