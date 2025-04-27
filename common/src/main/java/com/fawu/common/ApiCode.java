package com.fawu.common;

/**
 * 返回状态
 *
 * @author <a href="https://github.com/fawu-K">fawu.K</a>
 * @since 2023-11-29 10:16
 **/

public enum ApiCode {
    /**
     * 操作成功200
     **/
    SUCCESS(200, "操作成功", "Operation successful"),
    /**
     * 登录过期
     */
    OUT_LOGIN(401, "登录过期", "Login expired"),
    /**
     * 非法访问
     **/
    UNAUTHORIZED(401, "非法访问", "Illegal access"),
    /**
     * 没有权限
     **/
    NOT_PERMISSION(403, "没有权限", "No permission"),
    /**
     * 你请求的资源不存在
     **/
    NOT_FOUND(404, "你请求的资源不存在", "The resource you requested does not exist"),
    /**
     * 操作失败
     **/
    FAIL(500, "操作失败", "Operation failed"),
    /**
     * 系统未注册
     */
    NOT_REGISTER(1000, "未注册", "Unregistered"),
    /**
     * 注册码无效
     */
    BAD_REGISTER(1001, "注册码无效", "Invalid registration code"),
    /**
     * 注册码无效
     */
    EXPIRED_REGISTER(1002, "注册码过期", "Registration code expired"),
    /**
     * 未登录
     **/
    LOGIN_EXCEPTION(4000, "未登录", "Not logged in"),
    /**
     * 验证码校验异常
     **/
    VERIFICATION_CODE_EXCEPTION(4001, "验证码校验异常", "Verification code verification exception"),
    /**
     * 用户名或密码错误异常
     **/
    USERNAME_PASSWORD_EXCEPTION(4002, "用户名或密码错误", "Incorrect username or password"),
    /**
     * 登录失败
     **/
    LOGIN_FAILED_EXCEPTION(4003, "登录失败", "Login failed"),
    /**
     * 登录成功
     **/
    LOGIN_SUCCESS_EXCEPTION(4004, "登录成功", "Login succeeded"),
    /**
     * 登录授权异常
     **/
    AUTHENTICATION_EXCEPTION(4005, "登录授权异常", "Login authorization exception"),
    /**
     * JWT Token解析异常
     **/
    JWTDECODE_EXCEPTION(4006, "Token解析异常", "Token parsing exception"),
    /**
     * 未授权异常
     **/
    NOT_AUTHENTICATION_EXCEPTION(4007, "未授权", "Unauthorized"),
    /**
     * 重复登录异常
     **/
    REPEAT_LOGIN_EXCEPTION(4008, "重复登录","Repeated login"),
    /**
     * 用户未激活异常
     **/
    USER_NOT_ACTIVE_EXCEPTION(4009, "用户未激活", "User not activated"),
    /**
     * 用户停用异常
     **/
    USER_IS_FORBIDDEN_EXCEPTION(4010, "用户已停用", "User deactivated"),
    /**
     * 验证码过期
     */
    VERIFICATION_CODE_EXPIRED(4011, "验证码过期", "Verification code expired"),
    /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERROR(4012, "验证码错误", "Verification code error"),
    /**
     * 系统异常
     **/
    SYSTEM_EXCEPTION(5000, "系统异常", "System abnormality"),
    /**
     * 请求参数校验异常
     **/
    PARAMETER_EXCEPTION(5001, "请求参数校验异常", "Request parameter verification exception"),
    /**
     * 请求参数解析异常
     **/
    PARAMETER_PARSE_EXCEPTION(5002, "请求参数解析异常", "Request parameter parsing exception"),
    /**
     * 参数类型不匹配异常
     **/
    PARAMETER_TYPE_MISMATCH_EXCEPTION(5003, "参数类型不匹配", "Parameter type mismatch"),
    /**
     * HTTP内容类型异常
     **/
    HTTP_MEDIA_TYPE_EXCEPTION(5004, "HTTP内容类型异常", "HTTP content type exception"),
    /**
     * HTTP远程调用异常
     **/
    HTTP_CLIENT_ERROR_EXCEPTION(5005, "HTTP远程调用异常", "HTTP remote call exception"),
    /**
     * Controller处理异常
     */
    CONTROLLER_EXCEPTION(5106, "Controller处理异常", "Controller handles exceptions"),
    /**
     * 业务处理异常
     */
    BUSINESS_EXCEPTION(5107, "业务处理异常", "Business processing exception"),
    /**
     * Mapper处理异常
     */
    MAPPER_EXCEPTION(5108, "SQL处理异常", "SQL exception handling"),
    /**
     * 数据库处理异常
     */
    DAO_EXCEPTION(5109, "数据库处理异常", "Database processing exception"),
    /**
     * 数据库外键约束异常
     */
    DAO_CONSTRAINT_EXCEPTION(5112, "该数据存在其它关联信息，不能执行此操作", "This data has other associated information, so this operation cannot be performed"),
    /**
     * 微信小程序登录未关联账号
     */
    NO_WX_ASSOCIATION_EXCEPTION(5111, "当前微信账号未关联系统账号，请先前往关联账号后再操作！", "The current WeChat account is not associated with a system account. Please go to the associated account first before proceeding"),
    /**
     * 请求方法不支持
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(5110, "请求方法不支持异常", "The request method does not support exceptions"),
    /**
     * 自定义异常
     */
    CUSTOM_EXCEPTION(0, "未知", "unknown");

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 状态描述(英文)
     */
    private String messageEn;

    ApiCode(int code, String message, String messageEn) {
        this.code = code;
        this.message = message;
        this.messageEn = messageEn;
    }

    public static ApiCode getApiStatus(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    /**
     * 生成自定义异常
     *
     * @param code    异常代码
     * @param message 异常消息
     * @return 自定义异常
     */
    public static ApiCode customException(int code, String message) {
        ApiCode.CUSTOM_EXCEPTION.code = code;
        ApiCode.CUSTOM_EXCEPTION.message = message;
        return ApiCode.CUSTOM_EXCEPTION;
    }

    public int getCode() {
        return code;
        /*if(code != ApiStatus.SUCCESS.code){
            return 0;
        }else{
            return 1;
        }*/
    }

    public String getMessage() {
        return message;
    }

    public String getMessageEn() {
        return messageEn;
    }

}
