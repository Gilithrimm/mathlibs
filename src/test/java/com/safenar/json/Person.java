package com.safenar.json;

import java.util.Arrays;

public class Person {
    private String name;
    private int age;
    private String[] hobbies;
    private String address;

    public Person() {
    }

    public Person(String name, int age, String[] hobbies, String address) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", address='" + address + '\'' +
                '}';
    }
}
