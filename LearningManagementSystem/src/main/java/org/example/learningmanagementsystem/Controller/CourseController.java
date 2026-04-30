package org.example.learningmanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystem.ApiResponse.ApiResponse;
import org.example.learningmanagementsystem.Model.Course;
import org.example.learningmanagementsystem.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(courseService.updateCourse(id,course)){
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id){
        if(courseService.deleteCourse(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }

    @GetMapping("/get-by-category/{category}")
    public ResponseEntity<?> displayCoursesOfCategory(@PathVariable String category){
        if(courseService.displayCoursesOfCategory(category).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no course in this category"));
        }
        return ResponseEntity.status(200).body(courseService.displayCoursesOfCategory(category));
    }

    @GetMapping("/dispaly-free-courses")
    public ResponseEntity<?> displayFreeCourses(){
        if(courseService.displayFreeCourses().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no free courses now"));
        }
        return ResponseEntity.status(200).body(courseService.displayFreeCourses());
    }

    @PutMapping("set-fees/{id}/{fees}")
    public ResponseEntity<?> setFees(@PathVariable String id, @PathVariable double fees){
        int result = courseService.setFees(id,fees);
        if(result == 1){
            return ResponseEntity.status(400).body(new ApiResponse("The fees Should be more than zero to set"));
        }
        if(result == 2){
            return ResponseEntity.status(200).body(new ApiResponse("Fees set successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }

    @GetMapping("/get-less-than/{amount}")
    public ResponseEntity<?> showPaidCoursesLessThanAmount(@PathVariable double amount){
        if(courseService.showPaidCoursesLessThanAmount(amount).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There id no course is less than "+amount));
        }
        return ResponseEntity.status(200).body(courseService.showPaidCoursesLessThanAmount(amount));
    }
}
