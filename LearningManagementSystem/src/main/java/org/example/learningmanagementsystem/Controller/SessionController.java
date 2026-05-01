package org.example.learningmanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystem.ApiResponse.ApiResponse;
import org.example.learningmanagementsystem.Model.Session;
import org.example.learningmanagementsystem.Service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/get")
    public ResponseEntity<?> getSessions(){
        return ResponseEntity.status(200).body(sessionService.getSessions());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSession(@RequestBody @Valid Session session, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(sessionService.addSessions(session)) {
            return ResponseEntity.status(200).body(new ApiResponse("Session added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID already used"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSession(@PathVariable String id, @RequestBody @Valid Session session, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(sessionService.updateSession(id,session)){
            return ResponseEntity.status(200).body(new ApiResponse("Session updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Session not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable String id){
        if(sessionService.deleteSession(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Session deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Session not found"));
    }

    @PutMapping("/change-duration/{id}/{hours}")
    public ResponseEntity<?> changeSessionDuration(@PathVariable String id, @PathVariable int hours){
        int result = sessionService.changeSessionDuration(id,hours);
        if(result == 1){
            return ResponseEntity.status(400).body(new ApiResponse("Hours should be more than zero"));
        }
        if(result == 2){
            return ResponseEntity.status(200).body(new ApiResponse("Duration changed successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Session not found"));
    }

    @GetMapping("/future-sessions")
    public ResponseEntity<?> ShowFutureSessions(){
        if(sessionService.ShowFutureSessions().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no future sessions yet"));
        }
        return ResponseEntity.status(200).body(sessionService.ShowFutureSessions());
    }

    @GetMapping("/get-by-range/{minHour}/{maxHour}")
    public ResponseEntity<?> displaySessionsInHoursRange(@PathVariable int minHour,@PathVariable int maxHour){
        if(sessionService.displaySessionsInHoursRange(minHour,maxHour).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no sessions found in this range"));
        }
        return ResponseEntity.status(200).body(sessionService.displaySessionsInHoursRange(minHour,maxHour));
    }

    @DeleteMapping("/delete-all-course-sessions/{courseId}")
    public ResponseEntity<?> deleteAllSessionOfCourse(@PathVariable String courseId){
        if(sessionService.deleteAllSessionOfCourse(courseId)){
            return ResponseEntity.status(200).body(new ApiResponse("All session of the course deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The course don't have sessions to delete"));
    }
}
