package org.example.Payroll.Dao;


import org.example.Payroll.Employee;
import org.example.Payroll.Employees.CommissionEmployee;
import org.example.Payroll.Employees.HourlyEmployee;
import org.example.Payroll.Employees.SalariedCommissionEmployee;
import org.example.Payroll.Employees.SalariedEmployee;
import org.example.Payroll.event.SalaryChangeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDao  implements ApplicationEventPublisherAware {


    private ApplicationEventPublisher eventPublisher;

    @Value("${dataConnection.URL}")
    private    String URL;
    @Value("${dataConnection.USERNAME}")
    private   String USERNAME;
    @Value("${dataConnection.PASSWORD}")
    private    String PASSWORD;

    private  Connection connection;
    private  List<Employee> employeeList = new ArrayList<>();





    @PostConstruct
    private void getInit() throws SQLException {

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM HourlyEmployee");

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            HourlyEmployee hourlyEmployee = new HourlyEmployee();

            hourlyEmployee.setHourlySalary(resultSet.getDouble("hourlySalary"));
            hourlyEmployee.setOvertimeHour(resultSet.getInt("overtimeHour"));
            hourlyEmployee.setWorkedHour(resultSet.getInt("workedHour"));


            employeeList.add(hourlyEmployee);



            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM CommissionEmployee");

            ResultSet resultSet2 = preparedStatement2.executeQuery();


            resultSet2.next();

            CommissionEmployee commissionEmployee = new CommissionEmployee();

            commissionEmployee.setPercentage(resultSet2.getDouble("persentage"));
            commissionEmployee.setTotalCostOfNumOfSales(resultSet2.getDouble("totalCostOfNumOfSales"));

            employeeList.add(commissionEmployee);




            PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT * FROM SalariedCommissionEmployee");

            ResultSet resultSet3 = preparedStatement3.executeQuery();

            resultSet3.next();

            SalariedCommissionEmployee salariedCommissionEmployee = new SalariedCommissionEmployee();

            salariedCommissionEmployee.setBaseSalary(resultSet3.getDouble("BaseSalary"));
            salariedCommissionEmployee.setPercentage(resultSet3.getDouble("percentage"));
            salariedCommissionEmployee.setTotalCostOfNumOfSales(resultSet3.getDouble("totalcostofnumofsales"));

            employeeList.add(salariedCommissionEmployee);



            PreparedStatement preparedStatement4 = connection.prepareStatement("SELECT * FROM SalariedEmployee");

            ResultSet resultSet4 = preparedStatement4.executeQuery();

            resultSet4.next();

            SalariedEmployee salariedEmployee = new SalariedEmployee();

            salariedEmployee.setFixedMonthlySalary(resultSet4.getDouble("fixedMonthlySalary"));

            employeeList.add(salariedEmployee);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

            System.out.println("initialization db");

        }

    public void update(Employee employee) throws SQLException {
        for(Employee employee1 : employeeList){
            if(employee1.getType().equals(employee.getType())){

                SalariedCommissionEmployee salariedCommissionEmployee = (SalariedCommissionEmployee) employee1;


                salariedCommissionEmployee.setBaseSalary(salariedCommissionEmployee.getBaseSalary() + (salariedCommissionEmployee.getBaseSalary() * 0.1));

                PreparedStatement preparedStatement =
                        connection.prepareStatement("UPDATE salariedCommissionEmployee SET  baseSalary=? WHERE id=?");
                preparedStatement.setDouble(1, salariedCommissionEmployee.getBaseSalary());
                preparedStatement.setDouble(2, 1);

                preparedStatement.executeUpdate();

                this.eventPublisher.publishEvent(new SalaryChangeEvent(this, salariedCommissionEmployee));
            }
        }
    }


    public void  getEmployeeList() {
        for(Employee employee : employeeList){
            System.out.println(employee.getSalary() + "," + employee.getType());
        }

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}

