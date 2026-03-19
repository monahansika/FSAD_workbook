package com.klu.service;

import com.klu.model.Student;
import java.util.List;

public interface StudentService 
{
  Student createStudent(Student student);
  Student getStudentById(int id);
  List <Student> getAllStudents();
  Student updateStudent(int id,Student student);
  String deleteStudent(int id);
  List <Student> searchStudent(String name,String course);
}