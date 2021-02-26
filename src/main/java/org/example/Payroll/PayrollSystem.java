package org.example.Payroll;

import org.example.Payroll.Configuration.SpringConfig;
import org.example.Payroll.Dao.EmployeeDao;
import org.example.Payroll.Employees.SalariedCommissionEmployee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class PayrollSystem {
    private static Boolean bool = true;
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);



        SalariedCommissionEmployee salariedCommissionEmployee = context.getBean("salariedCommissionEmployee", SalariedCommissionEmployee.class);





        while (bool){
            System.out.println("1. To check salary");
            System.out.println("2. To add 10% to Salaried-Commission Employee");
            System.out.println("any. To Quit");
            String choice = read.readLine();

            switch (choice){
                case "1":
                    employeeDao.getEmployeeList();
                    break;
                case "2":

                    employeeDao.update(salariedCommissionEmployee);
                    break;
                default:
                    bool = false;

            }
        }






    }
}
