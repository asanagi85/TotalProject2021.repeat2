package gui;

import service.EmployeeManager;
import service.Manager;
import vo.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeUI implements UI{
    Scanner scanner;
    Manager manager;

    public EmployeeUI() {
        scanner = new Scanner(System.in);
        manager = new EmployeeManager();

        String select;

        while (true){
            printMainMenu();
            select = scanner.nextLine();

            switch (select){
                case "1":
                    insertEmployee();
                    break;
                case "2":
                    findEmployee();
                    break;
                case "3":
                    deleteEmployee();
                    break;
                case "4":
                    manager.showAll();
                    break;
                case "9":
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println("파일은 그곳에 모두 저장됩니다.");
                    manager.saveFile();
                    System.exit(0);
            }//switch
        }//while
    }

    @Override
    public void insertEmployee() {
        System.out.println("=========================================");
        System.out.println("============[KITA 직원 등록부]=============");
        System.out.println("=========================================");
        System.out.println("1. 사원번호 : " + (Employee.serial + 1));
        System.out.println("2. 사원이름 : ");
        String name = scanner.nextLine();


        System.out.println("3. 사원급여 : ");
        int salary = scanner.nextInt();

        ArrayList<String> license = new ArrayList<String>();

        while (true){
            Scanner licenseInput = new Scanner(System.in);
            System.out.println("4. >>>>>>>>>> License : ");
            String temp = licenseInput.nextLine();

            if(temp.equals("")){
                break;
            }//if

            license.add(temp);
        }//while

        Employee employee = new Employee(name, salary, license);
        manager.insertEmployee(employee);
    }

    @Override
    public void deleteEmployee() {
        System.out.println(" > 삭제하려는 사원의 사원번호 : ");
        String eid = scanner.nextLine();

        Employee e = manager.findEmployee(eid);

        if(e == null){
            System.out.println("삭제하고자 하는 사람이 없습니다.");
            return;
        }//if

        System.out.println(e.getName() + "님을 정말로 삭제 하시겠습니까?(Y/N)");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("y")){
            manager.deleteEmployee(eid);
        }//if
        else{
            System.out.println(e.getName() + "님의 삭제를 취소합니다.");
        }//else
    }

    @Override
    public void findEmployee() {
        System.out.println("검색하려는 사원의 번호를 입력하세요 : ");
        String eid = scanner.nextLine();

        Employee e = manager.findEmployee(eid);
        System.out.println(e);
    }

    @Override
    public void printMainMenu() {
        System.out.println("=========================================");
        System.out.println("==========[KITA 사원 관리 프로그램]=========");
        System.out.println("=========================================");
        System.out.println("1. 사원 등록");
        System.out.println("2. 사원 검색");
        System.out.println("3. 사원 삭제");
        System.out.println("4. 전체 사원 출력");
        System.out.println("9. 종료");
        System.out.println("=========================================");
        System.out.println("** 메뉴 번호 선택 : ");
    }
}