package com.crecema.my.spring.base.aop;

import com.crecema.my.spring.base.aop.config.Beans;
import com.crecema.my.spring.base.aop.domain.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AOPTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Beans.class);
        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);
        studentService.getStudent(1001);
        System.out.println("-------------------------");
        assertThrows(RuntimeException.class, () -> {
            studentService.getStudent(10001);
        });
        System.out.println("-------------------------");
        studentService.getStudentList("mahaoran");
    }

}
