package com.jsp.Notification.Controller;

import com.jsp.Notification.Dto.StudentDto;
import com.jsp.Notification.Helper.ResponseStructure;
import com.jsp.Notification.Model.Student;
import com.jsp.Notification.Service.NotificationService;
import com.jsp.Notification.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
public class StudentController
{
    @Autowired
    NotificationService notificationService;
    @Autowired
    StudentService studentService;
    /*@PostMapping("/")
    public String message(@RequestBody Student student){
        notificationService.sendNotification(student);
        return "done";
    }*/
    @PostMapping("/")
    public  ResponseStructure<Student> saveStudent(@RequestBody Student s){
        System.out.println(s);
        ResponseStructure<Student> responseStructure = studentService.studentSave(s);
        return responseStructure;
    }
    @GetMapping("/")
    public ResponseStructure<Student> getById(@RequestParam int id){

        ResponseStructure<Student> studentById = studentService.getStudentById(id);
        return studentById;
    }
    @PutMapping("/")
    public ResponseStructure<Student> update(@RequestParam int id,@RequestBody Student s){
        ResponseStructure<Student> responseStructure = studentService.updateStudent(id, s);
        return responseStructure;

    }

}
