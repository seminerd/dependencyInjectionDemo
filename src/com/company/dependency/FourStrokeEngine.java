package com.company.dependency;

@BeanId(id = "4s")
public class FourStrokeEngine implements Motive{

    @Override
    public void start() {
        System.out.println("Bruhm bruhmmm");
    }
}
