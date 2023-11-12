package com.hongjun.chatgpt;

public enum EnumMyError implements CommonError {
    ERROR_ONE(1, "Error One"),
    ERROR_TWO(2, "Error Two");
    // Add more errors as needed

    private final int errCode;
    private final String errMsg;

    EnumMyError(int errCode, String errMsg) {
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
        return new ErrorBuilder();
    }

    private static class ErrorBuilder implements CommonErrorBuilder {
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
            // Enum values cannot be instantiated outside of the enum,
            // so we use the builder to construct a new instance with custom error code and message.
            return new MyError(errCode, errMsg);
        }
    }
}
