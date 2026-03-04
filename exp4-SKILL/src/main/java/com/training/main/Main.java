package com.training.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.training.model.Student;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
            new ClassPathXmlApplicationContext("exp4-SKILL.xml");

        Student student = context.getBean("student", Student.class);
        student.display();
    }
}
