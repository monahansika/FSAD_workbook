package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.config.Appconfig;
import com.klu.model.Student;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Appconfig.class);

        Student student = context.getBean(Student.class);
        student.display();
    }
}
