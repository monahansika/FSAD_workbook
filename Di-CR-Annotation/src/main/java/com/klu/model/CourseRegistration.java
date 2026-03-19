package com.klu.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CourseRegistration {
	@Value("101")
    private int rollNo;
	@Value("hansika")
    private String studentName;
	@Value("fsad")
    private String CourseName;
	@Value("4")
    private int semester;

public CourseRegistration(int no, String name, String cn, int sem) {
   rollNo=no;
   studentName=name;
   CourseName=cn;
   semester=sem;
}

}