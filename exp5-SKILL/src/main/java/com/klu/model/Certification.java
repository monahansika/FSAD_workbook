package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id;
    private String name;
    private String dateOfCompletion;

    public Certification() {
        this.id = 201;
        this.name = "Java Full Stack";
        this.dateOfCompletion = "15-Jan-2025";
    }

    public void display() {
        System.out.println("Certification ID   : " + id);
        System.out.println("Certification Name : " + name);
        System.out.println("Completion Date    : " + dateOfCompletion);
    }
}