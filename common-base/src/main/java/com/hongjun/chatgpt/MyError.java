package com.hongjun.chatgpt;

public class MyError implements CommonError{
    private final int errCode;
    private final String errMsg;

    public MyError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    public static CommonErrorBuilder builder() {
        return new MyErrorBuilder();
    }

    private static class MyErrorBuilder implements CommonErrorBuilder {
        private int errCode;
        private String errMsg;

        @Override
        public CommonErrorBuilder setErrCode(int errCode) {
            this.errCode = errCode;
            return this;
        }

        @Override
        public CommonErrorBuilder setErrMsg(String errMsg) {
            this.errMsg = errMsg;
            return this;
        }

        @Override
        public CommonError build() {
            return new MyError(errCode, errMsg);
        }
    }
}
