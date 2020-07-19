package com.jremind.HrSerach;

import java.util.Scanner;

public class Search {
	private HrSearchInterface hrSearch;
	private Scanner sc;
	
	public void run() {
		sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("메뉴 선택!");
			System.out.println("====================");
			System.out.println("a. 사원정보 검색(이름)");
			System.out.println("b. 사원정보 검색(입사년)");
			System.out.println("c. 과거 업무이력 검색(사원 ID)");
			System.out.println("q. 종료");
			String num = sc.next();
			
			if(num.equals("q")) {
				System.out.println("프로그램 종료!!");
				break;
			}else if(num.equals("a")) {
				System.out.print("사원의 Frist Name 또는 Last name을 입력하세요: ");
				String name = sc.next();
				hrSearch = new EmployeeName(name); 
			}else if(num.equals("b")) {
				System.out.print("입사년도를 입력하세요: ");
				int year = sc.nextInt();
				hrSearch = new EmployeeYear(year);			
			}else if(num.equals("c")) {
				System.out.print("사원번호를 입력하세요: ");
				int id = sc.nextInt();
				hrSearch = new EmployeeHistory(id);
			}else {
				System.out.println("입력이 잘 못 되었습니다. [a, b, c, q] 중에서 선택해주세요.");
			}
			hrSearch.searchHr();
		}
		sc.close();
	}	
}