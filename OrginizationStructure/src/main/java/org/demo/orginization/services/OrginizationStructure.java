package org.demo.orginization.services;



import org.demo.orginization.domain.Employee;

import java.util.List;

public interface OrginizationStructure {
    void addSubordinate(List<Employee> employees);

    // Check salary conditions
     void checkSalaries(List<Employee> employees);

    // Check reporting line conditions
    void checkReportingLines(List<Employee> employees);
}
