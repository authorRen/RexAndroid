package com.rex.rexandroid.data;

/**
 * 接口请求返回的参数
 *
 * @author Ren ZeQiang
 * @since 2018/12/19.
 */
public class RequestMsg {
    public static final String RESULT = "results";
    public static final String CODE = "code";
    public static final String DESC = "desc";
    public static final int RESULT_OK = 1;

    public static final int CODE_ERROR_DEFAULT = -1;

    public static final int CODE_ERROR_TIMEOUT = 1000;
    public static final String MSG_ERROR_SOCKET_TIMEOUT = "网络连接超时，请检查您的网络状态，稍后重试";

}
