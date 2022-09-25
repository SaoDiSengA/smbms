package com.tao.pojo;


import lombok.Data;

import java.util.List;

//GET,SET,ToString，有参，无参构造
@Data
public class Teacher {
    private int id;
    private String name;
    private List<Student> students;
}
