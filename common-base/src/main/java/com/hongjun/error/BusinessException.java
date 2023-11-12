package com.hongjun.error;

/**
 * @author hongjun500
 * @date 2020/12/24 22:52
 * @tool ThinkPadX1隐士
 * Created with 2019.3.2.IntelliJ IDEA
 * Description: 包装器业务异常类实现
 */
public class BusinessException extends Exception implements CommonError {

    /**
     * 强关联一个 CommonError(实际上是一个由 EmBusinessError 枚举实现的类)
     */
    private final CommonError commonError;

    /**
     * 直接接收EmBusinessError的传参用于构造业务异常
     *
     * @param commonError 通用错误
     */
    public BusinessException(CommonError commonError) {
        // 此处的调用是由于自身继承了Exception,会有一些初始化机制
        super();
        this.commonError = commonError;
    }

    /**
     * 接受自定义的errMsg的方式构造业务异常
     *
     * @param commonError 通用错误
     * @param errMsg      错误信息
     */
    public BusinessException(CommonError commonError, String errMsg) {
        // 此处的调用是由于自身继承了Exception,会有一些初始化机制
        super();
        this.commonError = commonError;
        // 二次改写errMsg
        this.commonError.setErrMsg(errMsg);
    }

    public static void assertBusinessException(CommonError commonError) throws BusinessException {
        throw new BusinessException(commonError);
    }

    /**
     * 断言业务异常
     *
     * @param flag        业务判断条件
     * @param commonError 通用错误
     * @throws BusinessException 业务异常
     */
    public static void assertBusinessException(boolean flag, CommonError commonError) throws BusinessException {
        if (flag) {
            assertBusinessException(commonError);
        }
    }

    public static void assertBusinessException(CommonError commonError, String errMsg) throws BusinessException {
        throw new BusinessException(commonError, errMsg);
    }

    /**
     * 断言业务异常并且可以接收自定义错误
     *
     * @param flag        业务判断条件
     * @param commonError 通用错误
     * @param errMsg      错误信息
     * @throws BusinessException 业务异常
     */
    public static void assertBusinessException(boolean flag, CommonError commonError, String errMsg) throws BusinessException {
        if (flag) {
            assertBusinessException(commonError, errMsg);
        }
    }


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
