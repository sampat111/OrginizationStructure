package org.demo.orginization.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Employee {
   private Integer id;
    private String firstName;
    private String lastName;
    private Double salary;
    private Integer managerId;
    private List<Employee> subordinates=new ArrayList<>();


    // Link subordinates to their respective managers
    public void addSubordinate(Employee subordinate) {
        this.subordinates.add(subordinate);
    }
}
