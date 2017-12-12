package com.example.demo.invoke;

/**
 * Created by ywf on 17-12-6.
 */
public class ResultView {
    private String status = "0";
    private String msg = "ok";
    private Object data;

    public ResultView() {
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultView(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
