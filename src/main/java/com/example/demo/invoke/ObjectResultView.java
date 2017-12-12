package com.example.demo.invoke;

/**
 * Created by ywf on 17-12-6.
 */
public class ObjectResultView extends ResultView {
    private Object data;

    public ObjectResultView(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return BeanUtil.toObjectString(this);
    }

}
