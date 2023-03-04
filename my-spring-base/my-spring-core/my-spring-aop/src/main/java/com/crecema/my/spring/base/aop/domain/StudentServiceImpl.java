package com.crecema.my.spring.base.aop.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Setter
    private StudentRepository studentRepository;

    @Override
    public Student getStudent(Integer id) {
        System.out.println("StudentServiceImpl." + "getStudent");
        return studentRepository.selectOne(id);
    }

    @Override
    public List<Student> getStudentList(String name) {
        return studentRepository.selectList(name);
    }

}
