package com.crecema.my.spring.base.aop.domain;

import java.util.List;
import java.util.Objects;

public class StudentRepository {

    private static final List<Student> STUDENT_POOL = List.of(
            new Student(1001, "mahaoran"),
            new Student(1002, "haoranma"),
            new Student(1003, "haoranma")
    );

    public Student selectOne(Integer id) throws RuntimeException {
        if (id > 10000) {
            throw new RuntimeException("id > 10000");
        }
        return STUDENT_POOL.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Student> selectList(String name) {
        return STUDENT_POOL.stream()
                .filter(student -> student.getName().equals(name))
                .toList();
    }

}
