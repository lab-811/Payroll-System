package org.example.Payroll.Employees;

import org.example.Payroll.Employee;
import org.springframework.stereotype.Component;

@Component
public class SalariedEmployee implements Employee {


    private Double fixedMonthlySalary;

    @Override
    public String getType() {
       return  "Salaried";
    }

    @Override
    public double getSalary() {
        return fixedMonthlySalary;
    }


    public Double getFixedMonthlySalary() {
        return fixedMonthlySalary;
    }

    public void setFixedMonthlySalary(Double fixedMonthlySalary) {
        this.fixedMonthlySalary = fixedMonthlySalary;
    }
}
