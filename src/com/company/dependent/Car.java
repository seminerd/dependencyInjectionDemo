package com.company.dependent;

import com.company.dependency.MarkInject;
import com.company.dependency.Motive;

public class Car {
    private Motive motive;

    public void move(){
        motive.start();
    }

    @MarkInject(id = "v8")
    public void setMotive(Motive motive){
        this.motive = motive;
    }

}
