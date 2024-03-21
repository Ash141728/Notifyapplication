package com.jsp.Notification.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jsp.Notification.Model.Student;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    public void sendNotification(Student student){
        logger.info("Student created successfully: {}", student);
    }

}
