package com.example.demo.invoke;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ywf on 17-12-6.
 */
public class InvokeTest {
    public static void main(String[] args)throws Exception {
        User user=new User();
        user.setId("123456");
        user.setName("易文富");
        user.setAddress("都江堰");
        List<Friend> list=new ArrayList<>();
        Friend f1=new Friend();
        f1.setName("F1");
        f1.setPhone("13811111111");
        list.add(f1);

        Friend f2=new Friend();
        f2.setName("F2");
        f2.setPhone("13822222222");
        list.add(f2);
        user.setList(list);
        ObjectResultView obj=new ObjectResultView(user);
        System.out.println(obj.toString());

        /*
        * 得到类中的所有属性集合
        */
        //得到类对象
        Class userCla = (Class) user.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(user);//得到此属性的值

            System.out.println("name:"+f.getName()+"\t value = "+val);

            String type = f.getType().toString();//得到此属性的类型
            if (type.endsWith("String")) {
                System.out.println(f.getType()+"\t是String");
                f.set(user,"12") ;        //给属性设值
            }else if(type.endsWith("int") || type.endsWith("Integer")){
                System.out.println(f.getType()+"\t是int");
                f.set(user,12) ;       //给属性设值
            }else{
                System.out.println(f.getType()+"\t");
            }

        }

    }
}
