package org.example.learningmanagementsystem.Service;

import org.example.learningmanagementsystem.Model.Certificate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CertificateService {
    ArrayList<Certificate> certificates = new ArrayList<>();

    public ArrayList<Certificate> getCertificates(){
        return certificates;
    }

    public boolean addCertificates(Certificate certificate){
        for(Certificate certificate1: certificates){
            if(certificate1.getId().equals(certificate.getId())){
                return false;
            }
        }
        if(!certificate.isIssued()){
            certificate.setIssuedDate(null);
        }
        certificates.add(certificate);
        return true;
    }

    public boolean updateCertificate(String id,Certificate certificate){
        for(int i=0; i< certificates.size(); i++){
            if(certificates.get(i).getId().equals(id)){
                certificate.setId(id);
                certificates.set(i,certificate);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCertificate(String id){
        for(int i=0; i< certificates.size(); i++){
            if(certificates.get(i).getId().equals(id)){
                certificates.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Certificate> getStudentCertificates(String studentId){
        ArrayList<Certificate> certificateArrayList = new ArrayList<>();
        for(Certificate certificate: certificates){
            if(certificate.getStudentId().equals(studentId)){
               certificateArrayList.add(certificate);
            }
        }
        return certificateArrayList;
    }

    public ArrayList<Certificate> showIssuedCertificates(){
        ArrayList<Certificate> certificateArrayList = new ArrayList<>();
        for(Certificate certificate: certificates){
            if(certificate.isIssued()){
                certificateArrayList.add(certificate);
            }
        }
        return certificateArrayList;
    }

    public ArrayList<Certificate> showIssuesCertificateByRange(LocalDate firstDate, LocalDate secondDate){
        ArrayList<Certificate> certificateArrayList = new ArrayList<>();
        for(Certificate certificate: certificates){
            if(certificate.getIssuedDate().isAfter(firstDate) && certificate.getIssuedDate().isBefore(secondDate) && certificate.isIssued()){
                certificateArrayList.add(certificate);
            }
        }
        return certificateArrayList;
    }

    public int issuedCertificate(String id){
        for(Certificate certificate: certificates){
            if(certificate.getId().equals(id)){
                if(certificate.isIssued()){
                    return 1;
                }
                certificate.setIssued(true);
                certificate.setIssuedDate(LocalDate.now());
                return 2;
            }
        }
        return 3;
    }
}
