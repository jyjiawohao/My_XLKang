package com.sizu.mingteng.my_xianglekang.network;

/**
 * Created by lenovo on 2017/5/1.
 */

public class BaseBean<T> {
    /**
     * message : 请求成功
     * data : {"qr_url":"upload/QR/time_2017_02_16/xkNvqGhccZlJ1487231288547.png"}
     * code : 200
     */

    private String message;
    private T results;
    private boolean error;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
