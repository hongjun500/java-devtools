package com.hongjun.error;

/**
 * @author hongjun500
 * @date 2020/12/24 22:49
 * @tool ThinkPadX1隐士
 * Created with 2019.3.2.IntelliJ IDEA
 * Description: 通用返回对象---错误信息
 */
public interface CommonError {
    /**
     * 返回错误码
     * @return EnumBusinessError.getErrCode()
     */
    int getErrCode();

    /**
     * 返回错误信息
     * @return EnumBusinessError.getErrMsg()
     */
    String getErrMsg();

    /**
     * 自定义错误信息
     * @param errMsg 错误信息
     * @return EnumBusinessError
     */
    CommonError setErrMsg(String errMsg);
}
