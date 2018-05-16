package com.itschool;

public class Employee implements java.io.Serializable{
    public String name;
    public String address;
    public transient int number;  // поля с ключевім словом transient НЕ сериализуются

    public void mailCheck() {
        System.out.println("Mailing a check to " + name + " " + address);
    }
}
