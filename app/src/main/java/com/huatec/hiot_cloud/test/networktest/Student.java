package com.huatec.hiot_cloud.test.networktest;

import java.io.Serializable;

/**
 * 学生实体类
 */
class Student implements Serializable {
    //姓名
    private String name;
    //是否婚配
    private boolean married;
    //年龄
    private int age;
    //身高
    private int height;
    //是否毕业
     private boolean graduated;
     //id
    private int id;
    /**
     * 无参构造方法
     */
    public Student() {
    }

    /**
     * 有参构造方法
     *
     * @param name
     * @param married
     * @param age
     */
    public Student(String name, boolean married, int age, int height, boolean graduated, int id) {
        this.name = name;
//        this.married = married;
//        this.age = age;
        this.height = height;
        this.graduated = graduated;
        this.id = id;
    }

    /**
     * get and set 方法
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public boolean isMarried() {
//        return married;
//    }
//
//    public void setMarried(boolean married) {
//        this.married = married;
//    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
