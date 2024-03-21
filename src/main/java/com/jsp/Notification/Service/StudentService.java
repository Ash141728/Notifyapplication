package com.jsp.Notification.Service;

import com.jsp.Notification.Helper.ResponseStructure;
import com.jsp.Notification.Model.Student;
import com.jsp.Notification.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NotificationService notificationService;


    public ResponseStructure<List<Student>> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        ResponseStructure<List<Student>> responseStructure = new ResponseStructure<List<Student>>();
        responseStructure.setData(studentList);
        if (!studentList.isEmpty()) {

            responseStructure.setMessage("Student Data Fetched Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());

        } else {
            responseStructure.setMessage("Student Data Not found");
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        }
        return responseStructure;


    }

    public ResponseStructure<Student> getStudentById(int id) {
        ResponseStructure<Student> responseStructure = new ResponseStructure<Student>();

        try {
            Optional<Student> student = studentRepository.findById(id);
            Student student1 = student.get();
            responseStructure.setData(student1);
            responseStructure.setMessage("Student Details Fetched Successfully");
            responseStructure.setStatusCode(HttpStatus.FOUND.value());
        } catch (NoSuchElementException e) {
            responseStructure.setData(null);
            responseStructure.setMessage("Student Details Not Found");
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        }
        return responseStructure;
    }

    public ResponseStructure<Student> studentSave(Student student) {

        Student studentSave = studentRepository.save(student);
        ResponseStructure<Student> responseStructure = new ResponseStructure<Student>();
        responseStructure.setData(studentSave);
        responseStructure.setMessage("Student Data Saved");
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        notificationService.sendNotification(student);
        return responseStructure;
    }
    public ResponseStructure<Student> updateStudent(int id,Student studentDetails){
        Student student = getStudentById(id).getData();
        ResponseStructure<Student> responseStructure = new ResponseStructure<>();
        if(student!=null)
        {
            student.setStudentName(studentDetails.getStudentName());
            student.setStudentEmail(studentDetails.getStudentEmail());
            student.setStudentLoc(studentDetails.getStudentLoc());
            Student updateStudent = studentRepository.save(studentDetails);
            responseStructure.setData(updateStudent);
            responseStructure.setMessage("update done");
            responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());

            return responseStructure;
        }
        else{
            responseStructure.setData(null);
            responseStructure.setMessage("Student not found");
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            return responseStructure;
        }



    }
}