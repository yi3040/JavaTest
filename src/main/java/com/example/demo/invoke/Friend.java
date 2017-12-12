package com.example.demo.invoke;

/**
 * Created by ywf on 17-12-6.
 */
public class Friend {
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        String s = BeanUtil.toObjectString(this);
        return s == null ? super.toString() : s;
    }
}
