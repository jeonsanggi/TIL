package com.jremind.HrSearch;

import java.util.ArrayList;
import java.util.List;
import com.jremind.dao.HrDao;
import com.jremind.vo.Employee;

public class EmployeeYear implements HrSearchInterface {
	private HrDao dao;
	private List<Employee> employees;
	private int year;
	
	public EmployeeYear(int year) {
		this.dao = HrDao.getInstance();
		this.year = year;
	}

	@Override
	public void searchHr(){
		employees = new ArrayList<Employee>();
		employees = dao.getYearEmployees(year);
		printEmployee(employees);
	}
	
	@Override
	public void printEmployee(List<Employee> employees) {
		if(!employees.isEmpty()) {
			System.out.println(String.format("%10s%10s%30s%25s", "사원번호", "이름", "성", "부서"));
			System.out.println("===============================================================");
			
			for(Employee employee : employees) {
				System.out.print(String.format("%5s", employee.getEmployeeID()));
				System.out.print(String.format("%15s", employee.getFirstName()));
				System.out.print(String.format("%15s", employee.getLastName()));
				System.out.print(String.format("%15s", employee.getDepartmentName()));
				System.out.println();
			}
		}else {
			System.out.println("조회한 결과가 없습니다.!");
		}
	}

}