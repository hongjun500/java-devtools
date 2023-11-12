package com.hongjun.error;

public interface CommonErrorBuilder {
    CommonErrorBuilder setErrCode(int errCode);
    CommonErrorBuilder setErrMsg(String errMsg);
    CommonError build();
}
