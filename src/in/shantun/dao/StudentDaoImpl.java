package in.shantun.dao;

import in.shantun.dto.Student;
import java.sql.*;
import in.shantun.util.JdbcUtil;
import java.io.*;

public class StudentDaoImpl implements IStudentDao {
	
	Connection connection = null;

	@Override
	public String save(Student student) {
		String sqlInsertQuery = "insert into student_data(`name` , `email` , `city` , `country`) values(? , ? , ? ,?)";
		PreparedStatement pstmt = null;
		String status = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null) pstmt = connection.prepareStatement(sqlInsertQuery);
			if(pstmt != null) {
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getEmail());
				pstmt.setString(3, student.getCity());
				pstmt.setString(4, student.getCountry());
			}
			if(pstmt != null) {
				int rowAffected = pstmt.executeUpdate();
				if(rowAffected == 1) status = "SUCCESS";
				else status = "FAIL";
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
			status = "FAIL";
		}
		return status;
	}

	@Override
	public Student findById(Integer sid) {
		String sqlSelectQuery = "select * from student_data where id = ?";
		PreparedStatement pstmt = null;
		Student student = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null) pstmt = connection.prepareStatement(sqlSelectQuery);
			if(pstmt != null) {
				pstmt.setInt(1, sid);
			}
			if(pstmt != null) {
				ResultSet resultSet = pstmt.executeQuery();
				System.out.println("execute");
				if(resultSet.next()) {
					//copy the result set data and transfer it .
					student = new Student();
					
					student.setId(resultSet.getInt(1));
					student.setName(resultSet.getString(2));
					student.setEmail(resultSet.getString(3));
					student.setCity(resultSet.getString(4));
					student.setCountry(resultSet.getString(5));
				}
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateById(Student student) {
		String sqlUpdateQuery = "update student_data set name=? , email=? , city=? , country=? where id = ?";
		PreparedStatement pstmt = null;
		String status = null;
		try {
				connection = JdbcUtil.getJdbcConnection();
				if(connection != null) pstmt = connection.prepareStatement(sqlUpdateQuery);
				if(pstmt != null) {
					pstmt.setString(1, student.getName());
					pstmt.setString(2, student.getEmail());
					pstmt.setString(3, student.getCity());
					pstmt.setString(4, student.getCountry());
					pstmt.setInt(5, student.getId());
				}
				if(pstmt != null) {
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected == 1) status = "SUCCESS";
					else status = "FAIL";
				}
			}
			catch(SQLException | IOException e) {
				e.printStackTrace();
				status = "FAIL";
			}
			return status;
	}

	@Override
	public String deleteById(Integer sid) {
		String sqlDeleteQuery = "delete from student_data where id = ?";
		PreparedStatement pstmt = null;
		String status = null;
		try {
			Student student = findById(sid);
			if(student != null) 
			{
				connection = JdbcUtil.getJdbcConnection();
				if(connection != null) pstmt = connection.prepareStatement(sqlDeleteQuery);
				if(pstmt != null) pstmt.setInt(1, sid);
				if(pstmt != null) {
					int rowAffected = pstmt.executeUpdate();
					if(rowAffected == 1) status = "SUCCESS";
					else status = "FAIL";
				}
			}
			else status = "RECORD NOT AVAILABLE";
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return status;
	}

}
