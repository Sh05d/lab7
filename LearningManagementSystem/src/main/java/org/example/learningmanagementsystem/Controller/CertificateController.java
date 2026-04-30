package org.example.learningmanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystem.ApiResponse.ApiResponse;
import org.example.learningmanagementsystem.Model.Certificate;
import org.example.learningmanagementsystem.Service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping("/get")
    public ResponseEntity<?> getCertificates(){
        return ResponseEntity.status(200).body(certificateService.getCertificates());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCertificate(@RequestBody @Valid Certificate certificate, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        certificateService.addCertificates(certificate);
        return ResponseEntity.status(200).body(new ApiResponse("Certificate added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCertificate(@PathVariable String id, @RequestBody @Valid Certificate certificate, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(certificateService.updateCertificate(id,certificate)){
            return ResponseEntity.status(200).body(new ApiResponse("Certificate updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Certificate not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCertificate(@PathVariable String id){
        if(certificateService.deleteCertificate(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Certificate deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Certificate not found"));
    }

    @GetMapping("/student-certificates/{studentId}")
    public ResponseEntity<?> getStudentCertificates(@PathVariable String studentId){
        if(certificateService.getStudentCertificates(studentId).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("This student don't have any certification"));
        }
        return ResponseEntity.status(200).body(certificateService.getStudentCertificates(studentId));
    }

    @GetMapping("/issued-certificates")
    public ResponseEntity<?> showIssuedCertificates(){
        if(certificateService.showIssuedCertificates().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no issued certificate yet"));
        }
        return ResponseEntity.status(200).body(certificateService.showIssuedCertificates());
    }

    @GetMapping("/issued-range/{firstDate}/{secondDate}")
    public ResponseEntity<?> showIssuesCertificateByRange(@PathVariable LocalDate firstDate,@PathVariable LocalDate secondDate){
        if(certificateService.showIssuesCertificateByRange(firstDate,secondDate).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no issued certificate in this range"));
        }
        return ResponseEntity.status(200).body(certificateService.showIssuesCertificateByRange(firstDate, secondDate));
    }

    @PutMapping("/issued/{id}")
    public ResponseEntity<?> issuedCertificate( @PathVariable String id){
        if(certificateService.issuedCertificate(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Certificate issued successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Certificate not found"));
    }

}
