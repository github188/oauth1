package com.yitutech.common.error;

/**
 * Created by Easy
 */
public class CommonCode {

    /**
     * 执行结果：成功
     */
    public static final int OK = 200;

    /**
     * HTTP 错误
     */
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int INTERNAL_SERVER_ERROR = 500;

    /**
     * 术语库错误
     */
    public static final int DB_CONSTRAINT_VIOLATION = 601;




    /************    系统级别错误            ***************************/

    /**
     * 执行结果：系统错误
     */
    public static final int    SYS_ERROR                                        = -1;

    /**
     * 数据库执行错误
     */
    public static final int    SYS_DB_ERROR                                     = -2;

    /**
     * IO操作错误
     */
    public static final int    SYS_IO_ERROR                                     = -3;

    /**
     * 序列化错误
     */
    public static final int    SYS_SERIALIZE_ERROR                              = -4;

    /**
     * 日期格式化错误
     */
    public static final int    SYS_DATEFORMAT_ERROR                             = -5;

    /**
     * 文件上传错误
     */
    public static final int    SYS_FILEUPLOAD_ERROR                             = -6;

    /**
     * XML解析错误
     */
    public static final int    SYS_XML_PARSE_ERROR                              = -7;

    /**
     * 加密或解密数据异常
     */
    public static final int    SYS_ENCRYPT_ERROR                                = -8;

    /**
     * HTTP请求异常
     */
    public static final int    SYS_HTTP_REQUEST_ERROR                           = -9;

    /**
     * 非支持编码异常
     */
    public static final int SYS_UNSUPPORTED_ENCODING_ERROR                       = -10;



    /************    公共参数校验 　***************************/
    /**
     * 参数为空
     */
    public static final int    PARAM_IS_NULL                                    = 10001;

    /**
     *　参数错误
     */
    public static final int    PARAM_IS_WRONG                                    = 10002;


}
