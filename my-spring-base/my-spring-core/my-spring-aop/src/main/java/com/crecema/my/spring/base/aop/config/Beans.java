package com.crecema.my.spring.base.aop.config;

import com.crecema.my.spring.base.aop.domain.StudentRepository;
import com.crecema.my.spring.base.aop.domain.StudentService;
import com.crecema.my.spring.base.aop.domain.StudentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.crecema.my.spring.base.aop")
@EnableAspectJAutoProxy
public class Beans {

    @Bean
    public StudentRepository studentRepository() {
        return new StudentRepository();
    }

    @Bean
    public StudentService studentService() {
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentRepository(studentRepository());
        return studentService;
    }

}
