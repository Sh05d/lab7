package org.example.learningmanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystem.ApiResponse.ApiResponse;
import org.example.learningmanagementsystem.Model.Student;
import org.example.learningmanagementsystem.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(studentService.addStudent(student)) {
            return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID already used"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(studentService.updateStudent(id,student)){
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        if(studentService.deleteStudent(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @GetMapping("/display-student/{id}")
    public ResponseEntity<?> displayStudent(@PathVariable String id){
        if(studentService.displayStudent(id) == null){
            return ResponseEntity.status(400).body(new ApiResponse("Student not exist"));
        }
        return ResponseEntity.status(200).body(studentService.displayStudent(id));
    }

    @PutMapping("/reset-password/{id}/{newPassword}")
    public ResponseEntity<?> resetPassword(@PathVariable String id, @PathVariable String newPassword){
        if(studentService.resetPassword(id,newPassword) == 1){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid password. password must be at least 8 characters and contain uppercase and lowercase letters, number and special character"));
        }
        if(studentService.resetPassword(id,newPassword) == 2){
            return ResponseEntity.status(200).body(new ApiResponse("Password reset successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @PutMapping("/suspend-student/{id}")
    public ResponseEntity<?> suspendStudent(@PathVariable String id){
        int result = studentService.suspendStudent(id);
        if(result == 1){
            return ResponseEntity.status(400).body(new ApiResponse("Student already suspended"));
        }
        if(result == 2){
            return ResponseEntity.status(200).body(new ApiResponse("Student suspended successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @GetMapping("/get-by-status/{status}")
    public ResponseEntity<?> showStudentsByStatus(@PathVariable String status){
        if(studentService.showStudentsByStatus(status).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no student is "+status+" right now"));
        }
        return ResponseEntity.status(200).body(studentService.showStudentsByStatus(status));
    }

}
