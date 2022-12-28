package com.srs.control;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.srs.servicee.StudentService;
import com.srs.student.Student;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	@Autowired
	StudentService studentService;
	
//	------Get Method-----
	@RequestMapping(value="/getstudentdetails", method=RequestMethod.GET)
	public List<Student> getstudentdetails(){
		List<Student> studentList=studentService.getStudentDetailsFromDao();
		return studentList;
	}
	
//	-------Post Method-------
	@RequestMapping(value="/postuserdata",method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces =MediaType.ALL_VALUE)
	public String postUserData(@RequestBody Student student) {
		
		if(Objects.isNull(student.getName())&&"".equals(student.getName())){
			throw new IllegalArgumentException("Email id is not valid");
		}
		
		String statusMessage;
		
 		if(0==student.getId()) {
			throw new IllegalArgumentException("User id is not valid");
		}
		
		boolean status=studentService.saveDataToDb(student);
		
		if(status) {
			statusMessage="Data Inserted Successfully..!";
		}else {
			statusMessage="Data Inserted Un-Successfully..!";
		}
 		return statusMessage;
	}

	
}
