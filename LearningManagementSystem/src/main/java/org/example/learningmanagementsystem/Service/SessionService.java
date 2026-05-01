package org.example.learningmanagementsystem.Service;

import org.example.learningmanagementsystem.Model.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class SessionService {

    ArrayList<Session> sessions = new ArrayList<>();

    public ArrayList<Session> getSessions(){
        return sessions;
    }

    public boolean addSessions(Session session){
        for(Session session1: sessions){
            if(session1.getId().equals(session.getId())){
                return false;
            }
        }
        sessions.add(session);
        return true;
    }

    public boolean updateSession(String id,Session session){
        for(int i=0; i< sessions.size(); i++){
            if(sessions.get(i).getId().equals(id)){
                session.setId(id);
                sessions.set(i,session);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSession(String id){
        for(int i=0; i< sessions.size(); i++){
            if(sessions.get(i).getId().equals(id)){
                sessions.remove(i);
                return true;
            }
        }
        return false;
    }

    public int changeSessionDuration(String id, int hours){
        if(hours <= 0){
            return 1;
        }
        for(Session session: sessions){
            if(session.getId().equals(id)){
                session.setDurationHours(hours);
                return 2;
            }
        }
        return 3;
    }

    public ArrayList<Session> ShowFutureSessions(){
        ArrayList<Session> sessionArrayList = new ArrayList<>();
        for(Session session: sessions){
            if(session.getDate().isAfter(LocalDate.now())){
                sessionArrayList.add(session);
            }
        }
        return sessionArrayList;
    }

    public ArrayList<Session> displaySessionsInHoursRange(int minHour, int maxHour){
        ArrayList<Session> sessionArrayList = new ArrayList<>();
        for(Session session: sessions){
            if(session.getDurationHours() >= minHour && session.getDurationHours() <= maxHour){
                sessionArrayList.add(session);
            }
        }
        return sessionArrayList;
    }

    public boolean deleteAllSessionOfCourse(String courseId){
        boolean isExist = false;
        for(int i=0; i< sessions.size(); i++){
            if(sessions.get(i).getCourseId().equals(courseId)){
                sessions.remove(i);
                isExist = true;
                i--;
            }
        }
        return isExist;
    }
}
