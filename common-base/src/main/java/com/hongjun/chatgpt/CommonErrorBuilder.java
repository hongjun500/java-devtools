package com.hongjun.chatgpt;

public interface CommonErrorBuilder {
    CommonErrorBuilder setErrCode(int errCode);
    CommonErrorBuilder setErrMsg(String errMsg);
    CommonError build();
}