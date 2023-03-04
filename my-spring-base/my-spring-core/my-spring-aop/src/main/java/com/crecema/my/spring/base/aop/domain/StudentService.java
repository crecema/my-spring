package com.crecema.my.spring.base.aop.domain;

import java.util.List;

public interface StudentService {

    Student getStudent(Integer id);

    List<Student> getStudentList(String name);

}
