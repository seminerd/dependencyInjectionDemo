package com.company.dependency;

@BeanId(id = "v8")
public class V8Engine implements Motive{
    @Override
    public void start() {
        System.out.println("VRUHMMMMMMMMMMMMMMMMMMMMM");
    }
}
