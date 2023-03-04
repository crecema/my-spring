package com.crecema.my.spring.base.aop.config;

import com.crecema.my.spring.base.aop.domain.Student;
import com.crecema.my.spring.base.aop.domain.StudentService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAspect {

    // 切点 pointcut
    @Pointcut("execution(* getStudent(*))")
    public void allGetStudent() {}
    @Pointcut("execution(* getStudentList(*)) && args(name)")
    public void allGetStudentList(String name) {}

    // 建议 advice
    @Before("allGetStudent()")
    private void beforeSomething(JoinPoint joinPoint) {
        StudentService studentService = (StudentService) joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        System.out.println("before something");
    }

    @AfterReturning(pointcut = "allGetStudent()", returning = "student")
    private void afterReturnSomething(Student student) {
        System.out.println("after return " + student);
    }

    @AfterThrowing(pointcut = "allGetStudent()", throwing = "ex")
    private void afterThrowException(RuntimeException ex) {
        System.out.println("after throw exception " + ex.getMessage());
    }

    @After("allGetStudent()")
    private void afterSomething() {
        System.out.println("after something");
    }

    @Around("allGetStudent()")
    private Object aroundSomething(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around something");
        Object proceed = pjp.proceed();
        System.out.println("around something");
        return proceed;
    }

    @Before(value = "allGetStudentList(name)", argNames = "name")
    private void beforeSomething2(String name) {
        System.out.println("before allGetStudentList() name=" + name);
    }

}
