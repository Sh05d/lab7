package org.example.learningmanagementsystem.Service;

import org.example.learningmanagementsystem.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public boolean addCourse(Course course){
        for(Course course1: courses){
            if(course1.getId().equals(course.getId())){
                return false;
            }
        }
        if(!course.isPaid()){
            course.setFees(0);
        }
        courses.add(course);
        return true;
    }

    public boolean updateCourse(String id,Course course){
        for(int i=0; i< courses.size(); i++){
            if(courses.get(i).getId().equals(id)){
                if(!course.isPaid()){
                    course.setFees(0);
                }
                course.setId(id);
                courses.set(i,course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id){
        for(int i=0; i< courses.size(); i++){
            if(courses.get(i).getId().equals(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> displayCoursesOfCategory(String category){
        ArrayList<Course> courseArrayList = new ArrayList<>();
        for(Course course: courses){
            if(course.getCategory().equalsIgnoreCase(category)){
                courseArrayList.add(course);
            }
        }
        return courseArrayList;
    }

    public ArrayList<Course> displayFreeCourses(){
        ArrayList<Course> courseArrayList = new ArrayList<>();
        for(Course course: courses){
            if(!course.isPaid()){
                courseArrayList.add(course);
            }
        }
        return courseArrayList;
    }

    public int setFees(String id, double fees){
        if(fees <= 0){
            return 1;
        }
        for(Course course: courses){
            if(course.getId().equals(id)){
                course.setFees(fees);
                course.setPaid(true);
                return 2;
            }
        }
        return 3;
    }

    public ArrayList<Course> showPaidCoursesLessThanAmount(double amount){
        ArrayList<Course> courseArrayList = new ArrayList<>();
        for(Course course: courses){
            if(course.getFees() < amount && course.isPaid()){
                courseArrayList.add(course);
            }
        }
        return courseArrayList;
    }
}
