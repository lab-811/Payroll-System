package org.example.Payroll.Employees;

import org.example.Payroll.Employee;
import org.springframework.stereotype.Component;

@Component
public class CommissionEmployee implements Employee {

    private double totalCostOfNumOfSales;
    private double percentage;


    @Override
    public String getType() {
        return "Commission";
    }

    @Override
    public double getSalary() {
        double salary = totalCostOfNumOfSales * percentage;
        return salary;
    }

    public double getTotalCostOfNumOfSales() {
        return totalCostOfNumOfSales;
    }

    public void setTotalCostOfNumOfSales(double totalCostOfNumOfSales) {
        this.totalCostOfNumOfSales = totalCostOfNumOfSales;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
