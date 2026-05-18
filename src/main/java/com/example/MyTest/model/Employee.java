package com.example.MyTest.model;

public class Employee {
    private long id;
    private String name;
    private double salary;
    Employee(){}
    Employee(long id,String name,double salary){
        this.id=id;
        this.name=name;
        this.salary=salary;
    }
    public void setId(int id){
        this.id=id;
    }
    public long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSalary(double salary){
        this.salary=salary;
    }
    public double getSalary(){
        return salary;
    }

}
