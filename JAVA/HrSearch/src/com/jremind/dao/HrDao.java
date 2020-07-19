package com.jremind.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jremind.vo.Employee;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class HrDao {
	private static HrDao dao = new HrDao();
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement pStmt;
	private CallableStatement cstmt;
    private OracleCallableStatement ocstmt;
	
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String username = "c##hr";
	private final String password = "1234";
	
	private HrDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HrDao getInstance() {
		return dao;
	}
	
	private void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeConnection() {
		try {
			if(rSet != null)
				rSet.close();
			if(pStmt != null)
				pStmt.close();
			if(cstmt != null)
				cstmt.close();
			if(ocstmt != null)
				ocstmt.close();
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Employee> getNameEmployees(String name){
		List<Employee> employees = new ArrayList<Employee>();
		
		getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE ");
		sb.append("FROM EMPLOYEES ");
		sb.append("WHERE FIRST_NAME LIKE ? OR LAST_NAME LIKE ?");
		
		String sql = sb.toString();
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + name + "%");
			pStmt.setString(2, "%" + name + "%");
			
			rSet = pStmt.executeQuery();
			Employee employee;
			
			while(rSet.next()) {
				employee = new Employee();
				employee.setFirstName(rSet.getString(1));
				employee.setLastName(rSet.getString(2));
				employee.setEmail(rSet.getString(3));
				employee.setPhoneNumber(rSet.getString(4));
				employee.setHireDate(rSet.getString(5));
				
				employees.add(employee);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();		
		}
		
		return employees;
		
	}
	
	public List<Employee> getYearEmployees(int year){
		List<Employee> employees = new ArrayList<Employee>();
		
		getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, NVL(DEPARTMENT_NAME, '<Not Assigned>') AS DEPT_NAME  ");
		sb.append("FROM EMPLOYEES E LEFT OUTER JOIN DEPARTMENTS D ");
		sb.append("ON E.DEPARTMENT_ID = D.DEPARTMENT_ID ");
		sb.append("WHERE TO_CHAR(HIRE_DATE, 'YYYY') = ?");
		
		String sql = sb.toString();
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, year);
			
			rSet = pStmt.executeQuery();
			Employee employee;
			
			while(rSet.next()) {
				employee = new Employee();
				employee.setEmployeeID(rSet.getInt(1));
				employee.setFirstName(rSet.getString(2));
				employee.setLastName(rSet.getString(3));
				employee.setDepartmentName(rSet.getString(4));

				employees.add(employee);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();		
		}
		
		return employees;
	}
	
	
	public List<Employee> getHistoryEmployees(int id){
		List<Employee> employees = new ArrayList<Employee>();
		
		getConnection();
						
		try {
			cstmt = conn.prepareCall("BEGIN CURSOR_PKG.SP_JOB_HISTORY(?,?); END;");
			cstmt.setInt(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstmt.executeQuery();
			
			ocstmt = (OracleCallableStatement)cstmt;
			rSet = ocstmt.getCursor(2);
			Employee employee;

			while(rSet.next()) {
				employee = new Employee();
				employee.setFullName(rSet.getString(1));
				employee.setTitle(rSet.getString(2));
				employee.setHireDate(rSet.getString(3));
				employee.setEndDate(rSet.getString(4));

				employees.add(employee);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();		
		}
		
		return employees;
	}
}