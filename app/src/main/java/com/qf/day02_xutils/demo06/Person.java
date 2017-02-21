package com.qf.day02_xutils.demo06;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 1, 在类名之前添加@Table(name = "person")标签, 表示当前的类会被创建成一个表, name的值代码表的名称
 *
 * 2, 类中的属性需要添加@Column(name = "")标签, name代码的数据表中的字段
 *
 * 3, 在类中一定要有一个属性是主键, 否则表不会创建成功,
 *
 * isId = true代表是主键
 *
 * autoGen = true 代表自动增长, 如果不写, 默认自动增长
 */

@Table(name = "user")
public class Person {

    @Column(name = "id",isId = true)
    private  int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person(int id, String name, int age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Person() {
    }
}
