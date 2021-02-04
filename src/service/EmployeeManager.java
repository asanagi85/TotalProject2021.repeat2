package service;

import vo.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements Manager{
    BufferedReader br;
    BufferedWriter bw;
    FileReader fr;
    FileWriter fw;
    List<Employee> eList;
    File file;

    public EmployeeManager() {
        // D:\test text\employee2.txt
        file = new File("D:\\test text\\employee2.txt");
        if(file.exists()){
            eList = new ArrayList<>();
            getFile();
        }
        else{
            try{
                file.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }//catch
        }//else
    }//EmployeeManager()

    @Override
    public void getFile() {
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String temp;
            while(true){
                temp = br.readLine();
                if(temp == null){
                    break;
                }//if

                List<String> license = new ArrayList<>();
                String[] data = temp.split(",");
                Employee emp = new Employee();
                Employee.serial = Integer.parseInt(data[0]);
                emp.setName(data[1]);
                emp.setSalary(Integer.parseInt(data[2]));

                for(int i = 3; i < data.length; ++i){
                    license.add(data[i]);
                }//for

                if(license.size() != 0){
                    emp.setLicense(license);
                }

                eList.add(emp);
            }//while
        }//try
        catch (Exception e){
            e.printStackTrace();
        }//catch
        finally {
            try{
                if(fr != null) {
                    fr.close();
                }//if

                if(br != null){
                    br.close();
                }//if
            }//try
            catch (Exception e){
                e.printStackTrace();
            }//catch
        }//finally
    }

    @Override
    public void saveFile() {
        try{
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            for(Employee e : eList){
                bw.write(e.toString() + "\n");
            }//for
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(bw != null){
                    bw.close();
                }//if
                if(fw != null) {
                    fw.close();
                }//if

            }//try
            catch (Exception e){
                e.printStackTrace();
            }//catch
        }//finally

    }

    @Override
    public boolean insertEmployee(Employee employee) {
        return eList.add(employee);
    }

    @Override
    public boolean deleteEmployee(String eid) {
        for(Employee e : eList){
            if(e.getEid().equals(eid)){
                eList.remove(e);
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee findEmployee(String eid) {
        for(Employee e : eList){
            if(e.getEid().equals(eid)){
                return e;
            }//if
        }//for
        return null;
    }

    @Override
    public void showAll() {
        System.out.println(eList.size());
        for(Employee e : eList){
            System.out.println(e.toString());
        }
    }
}
