package com.jremind.HrSerach;

import java.util.ArrayList;
import java.util.List;
import com.jremind.dao.HrDao;
import com.jremind.vo.Employee;

public class EmployeeName implements HrSearchInterface{
	private HrDao dao;
	private List<Employee> employees;
	private String name;
	
	public EmployeeName(String name) {
		this.dao = HrDao.getInstance();
		this.name = name;
	}

	@Override
	public void searchHr() {
		employees = new ArrayList<Employee>();
		employees = dao.getNameEmployees(name);
		printEmployee(employees);
		
	}
	
	@Override
	public void printEmployee(List<Employee> employees) {
		if(!employees.isEmpty()) {
			System.out.println(String.format("%10s%10s%10s%25s%30s", "이름", "성", "이메일", "전화", "입사일"));
			System.out.println("===============================================================");
			
			for(Employee employee : employees) {
				System.out.print(String.format("%5s", employee.getFirstName()));
				System.out.print(String.format("%10s", employee.getLastName()));
				System.out.print(String.format("%10s", employee.getEmail()));
				System.out.print(String.format("%20s", employee.getPhoneNumber()));
				System.out.println(String.format("%20s", employee.getHireDate()));
				System.out.println();
			}
		}else {
			System.out.println("조회한 결과가 없습니다.!");
		}
	}
}