package org.example.Payroll.Employees;

import org.example.Payroll.Employee;
import org.springframework.stereotype.Component;

@Component
public class HourlyEmployee implements Employee {

    private int workedHour;
    private double HourlySalary;
    private int overtimeHour;

    @Override
    public String getType() {

        return "Hourly";
    }

    @Override
    public double getSalary() {
        double salary = (workedHour * HourlySalary) + (overtimeHour * (HourlySalary * 1.5));
        return salary;
    }

    public int getWorkedHour() {
        return workedHour;
    }

    public void setWorkedHour(int workedHour) {
        this.workedHour = workedHour;
    }

    public double getHourlySalary() {
        return HourlySalary;
    }

    public void setHourlySalary(double hourlySalary) {
        HourlySalary = hourlySalary;
    }

    public int getOvertimeHour() {
        return overtimeHour;
    }

    public void setOvertimeHour(int overtimeHour) {
        this.overtimeHour = overtimeHour;
    }
}
