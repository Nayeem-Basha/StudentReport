package com.srs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.srs.student.Student;
@Component
public class StudentDao {
	@Autowired
	JdbcTemplate jdbctemplate;

	public  List<Student> getStudentDetailsFromDb() {
		 List<Student> studentList=new ArrayList<>();
		return jdbctemplate.query("select*from srs.registor", new ResultSetExtractor<List<Student>>() {

			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				while(rs.next()) {
					Student students=new Student();
					students.setId(rs.getInt("id"));
					students.setName(rs.getString("name"));
					students.setSemester(rs.getString("semester"));
					students.setEnglish(rs.getInt("english"));
					students.setMaths(rs.getInt("maths"));
					students.setScience(rs.getInt("science"));
					studentList.add(students);
			
				}
				return studentList;
			}
		});
	}

	public boolean saveStudentsToStudentsTable(Student student) {
		// TODO Auto-generated method stub
		boolean status=false;
		
		String sql="insert into srs.registor(id,name,semester,english,maths,science)"+"values("+student.getId()+",'"+student.getName()+"','"
		+student.getSemester()+"','"+student.getEnglish()+"','"+student.getMaths()+"','"+student.getScience()+"')";
				try {
					jdbctemplate.execute(sql);
					status=true;
				}catch(Exception e) {
					e.printStackTrace();
				}
		return status;
	}

}
