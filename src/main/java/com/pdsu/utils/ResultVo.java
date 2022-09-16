package com.pdsu.utils;

/**
 *  告诉前台验证码不正确
 *   统一返回前台的格式
 *      code 标识    200 后台返回的数据成功   500  后台有问题
 *      message 提示消息   验证码不正确
 *      data   代表返回给前台的数据
 *
 *
 *
 */

public class ResultVo {
    private int code;//状态码
    private String message;//提示消息
    private Object data;//数据

    public ResultVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
