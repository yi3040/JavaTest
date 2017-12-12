package com.example.demo.invoke;

import java.util.List;

/**
 * Created by ywf on 17-12-6.
 */
public class User {
    private String id;
    private String name;
    private String address;

    private List<Friend> list;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Friend> getList() {
        return list;
    }

    public void setList(List<Friend> list) {
        this.list = list;
    }

    /*@Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }*/
    @Override
    public String toString() {
        String s = BeanUtil.toObjectString(this);
        return s == null ? super.toString() : s;
    }
}
