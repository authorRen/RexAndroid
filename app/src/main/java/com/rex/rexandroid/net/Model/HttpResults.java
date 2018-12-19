package com.rex.rexandroid.net.Model;

/**
 * 统一接口返回格式.
 *
 * @author Ren ZeQiang
 * @since 2018/12/19.
 */
public class HttpResults<T> {
    public int code;
    public String desc;
    public T results;
}
