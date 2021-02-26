package org.example.Payroll.Employees;

import org.example.Payroll.Employee;
import org.springframework.stereotype.Component;

@Component
public class SalariedCommissionEmployee implements Employee {

    private double baseSalary;
    private double totalCostOfNumOfSales;
    private double percentage;

    @Override
    public String getType() {
        return "SalariedCommission";
    }

    @Override
    public double getSalary() {
        double salary = baseSalary + totalCostOfNumOfSales * percentage;
        return salary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
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
