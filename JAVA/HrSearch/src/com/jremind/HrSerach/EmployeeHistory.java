package com.jremind.HrSerach;

import java.util.ArrayList;
import java.util.List;
import com.jremind.dao.HrDao;
import com.jremind.vo.Employee;

public class EmployeeHistory implements HrSearchInterface{
	private HrDao dao;
	private List<Employee> employees;
	private int id;
	
	public EmployeeHistory(int id) {
		this.dao = HrDao.getInstance();
		this.id = id;
	}
	
	@Override
	public void searchHr() {
		employees = new ArrayList<Employee>();
		employees = dao.getHistoryEmployees(id);
		printEmployee(employees);
		
	}
	
	@Override
	public void printEmployee(List<Employee> employees) {
		if(!employees.isEmpty()) {
			System.out.println(String.format("%10s%40s%35s%35s", "이름", "담당업무", "시작일", "종료일"));
			System.out.println("======================================================================================");
			
			for(Employee employee : employees) {
				System.out.print(String.format("%5s", employee.getFullName()));
				System.out.print(String.format("%25s", employee.getTitle()));
				System.out.print(String.format("%25s", employee.getHireDate()));
				System.out.print(String.format("%25s", employee.getEndDate()));
				System.out.println();
			}
		}else {
			System.out.println("조회한 결과가 없습니다.!");
		}
	}
}