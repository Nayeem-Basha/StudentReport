package com.srs.servicee;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srs.dao.StudentDao;
import com.srs.student.Student;
@Service
public class StudentService {
	@Autowired
	StudentDao studentDao;
	public  List<Student> getStudentDetailsFromDao() {
		// TODO Auto-generated method stub
		List<Student> studentList=studentDao.getStudentDetailsFromDb();
		return studentList;
	}
	public boolean saveDataToDb(Student student) {
		// TODO Auto-generated method stub
		return studentDao.saveStudentsToStudentsTable(student);
	}
}
