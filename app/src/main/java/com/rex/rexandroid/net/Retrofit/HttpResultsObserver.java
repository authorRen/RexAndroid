package com.rex.rexandroid.net.Retrofit;

import com.google.gson.stream.MalformedJsonException;
import com.rex.rexandroid.data.RequestMsg;
import com.rex.rexandroid.net.Model.HttpResults;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.CancellationException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 接口数据观察者.
 *
 * @author Ren ZeQiang
 * @since 2018/12/19.
 */
public abstract class HttpResultsObserver<T> implements Observer<HttpResults<T>> {

    private Disposable mDisposable;
    private boolean mIsCheckResultsNull = true;//有的接口返回成功(code == 1),但是results == null(原则上code == 1时results不能为null),无法统一判断.

    public HttpResultsObserver() {
    }

    public HttpResultsObserver(boolean mIsCheckResultsNull) {
        this.mIsCheckResultsNull = mIsCheckResultsNull;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(HttpResults<T> httpResults) {
        if (httpResults != null) {
            if (httpResults.code == RequestMsg.RESULT_OK) {
                checkResults(httpResults);
            } else {
                onFailure(httpResults.code, httpResults.desc);
            }
        } else {
            onFailure(RequestMsg.CODE_ERROR_DEFAULT, "");
        }
    }

    private void checkResults(HttpResults<T> httpResults) {
        if (mIsCheckResultsNull) {
            if (httpResults.results != null) {
                onSuccess(httpResults.results, httpResults.desc != null ? httpResults.desc : "");
            } else {
                onFailure(RequestMsg.CODE_ERROR_DEFAULT, httpResults.desc);
            }
        } else {
            //不检查httpResults.results是否为null，适用于接口不关注results,而是desc，简单的显示Toast的情况.
            onSuccess(httpResults.results, httpResults.desc != null ? httpResults.desc : "");
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            if (!(e instanceof CancellationException)) {
                if (e instanceof SocketTimeoutException) {
                    onFailure(RequestMsg.CODE_ERROR_TIMEOUT, RequestMsg.MSG_ERROR_SOCKET_TIMEOUT);
                } else if (e instanceof ConnectException) {
                } else if (e instanceof UnknownHostException) {
                } else if (e instanceof MalformedJsonException) {
                } else if (e instanceof SSLHandshakeException) {
                } else {
                }
            } else {
            }
        } else {
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T t, String msg);

    public void onFailure(int errorCode, String msg) {
        //失败情况---根据需求可以统一处理
    }

    public void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
