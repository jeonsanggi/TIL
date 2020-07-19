package com.jremind.HrSerach;

import java.util.List;

import com.jremind.vo.Employee;

public interface HrSearchInterface {
	void searchHr();
	void printEmployee(List<Employee> list);
}