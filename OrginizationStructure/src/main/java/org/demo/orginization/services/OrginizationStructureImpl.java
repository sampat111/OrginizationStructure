package org.demo.orginization.services;


import lombok.extern.slf4j.Slf4j;
import org.demo.orginization.domain.Employee;


import java.util.List;
import java.util.logging.Logger;

public class OrginizationStructureImpl implements OrginizationStructure {

    @Override
    public void addSubordinate(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.getManagerId() != null) {
                Employee manager = employees.stream().filter(e->e.getId().equals(employee.getManagerId())).findAny().orElse(null);
                if (manager != null) {
                    manager.addSubordinate(employee);
                }
            }
        }
    }

    @Override
    public void checkSalaries(List<Employee> employees) {
        for (Employee manager : employees) {
            if (manager.getSubordinates().isEmpty()) continue;

            double avgSubSalary = manager.getSubordinates().stream().mapToDouble(Employee::getSalary).average().orElse(0);
            double lowerBound = (avgSubSalary*20)/100+avgSubSalary;
            double upperBound = (avgSubSalary*50)/100+avgSubSalary;

            if (manager.getSalary() < lowerBound) {

                System.out.printf("Manager %s %s (ID: %d) earns %.2f less than they should.\n",
                        manager.getFirstName(), manager.getLastName(), manager.getId(), lowerBound - manager.getSalary());
            } else if (manager.getSalary() > upperBound) {
                System.out.printf("Manager %s %s (ID: %d) earns %.2f more than they should.\n",
                        manager.getFirstName(), manager.getLastName(), manager.getId(), manager.getSalary() - upperBound);
            }
        }
    }

    @Override
    public void checkReportingLines(List<Employee> employees) {
        for (Employee employee : employees) {
            int reportingLineLength = getReportingLineLength(employees,employee);
            if (reportingLineLength > 4) {
                System.out.printf("Employee %s %s (ID: %d) has  %d extra managers between them and the CEO.", employee.getFirstName(), employee.getLastName(), employee.getId(), reportingLineLength - 4);
            }
        }

    }
    private static int getReportingLineLength(List<Employee> employees,Employee employee) {
        int length = 0;
        Employee currentEmployee = employee;

        while (currentEmployee.getManagerId() != null) {
            length++;
            Employee finalCurrentEmployee = currentEmployee;
            currentEmployee = employees.stream().filter(e->e.getId().equals(finalCurrentEmployee.getManagerId())).findAny().orElse(null);
        }

        return length;
    }
}
