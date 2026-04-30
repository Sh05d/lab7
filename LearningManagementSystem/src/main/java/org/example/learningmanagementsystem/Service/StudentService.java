package org.example.learningmanagementsystem.Service;

import org.example.learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students =new ArrayList<>();

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public boolean updateStudent(String id,Student student){
        for(int i=0; i< students.size(); i++){
            if(students.get(i).getId().equals(id)){
                students.set(i,student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id){
        for(int i=0; i< students.size(); i++){
            if(students.get(i).getId().equals(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student displayStudent(String id){
        for(Student student: students){
            if(student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }

    public int resetPassword(String id, String newPassword){
        if(!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$")){
            return 1;
        }
        for(Student student: students){
            if(student.getId().equals(id)){
                student.setPassword(newPassword);
                return 2;
            }
        }
        return 3;
    }

    public int suspendStudent(String id){
        for(Student student: students){
            if(student.getId().equals(id)){
                if(student.getStatus().equalsIgnoreCase("Suspended")){
                    return 1;
                }
                student.setStatus("Suspended");
                return 2;
            }
        }
        return 3;
    }

    public ArrayList<Student> showStudentsByStatus(String status){
        ArrayList<Student> studentArrayList = new ArrayList<>();
        for(Student student: students){
            if(student.getStatus().equalsIgnoreCase(status)){
                studentArrayList.add(student);
            }
        }
        return studentArrayList;
    }


}
