package com.company;

import com.company.assembler.DIAssembler;
import com.company.dependent.Bike;
import com.company.dependent.Car;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
	DIAssembler assembler = new DIAssembler();
    Bike bike = assembler.performInject(Bike.class);
    bike.move();
    Car car = assembler.performInject(Car.class);
    car.move();
    }
}
