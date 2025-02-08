package org.demo.orginization;

import com.sun.istack.internal.logging.Logger;

import org.demo.orginization.domain.Employee;
import org.demo.orginization.services.OrginizationStructure;
import org.demo.orginization.services.OrginizationStructureImpl;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;


import static org.demo.orginization.utils.CsvFileUtil.readAndParseCsv;


public class OrginizationStructureApp {
    static Logger logger = Logger.getLogger(OrginizationStructureApp.class.getName().getClass());
    public static void main(String[] args) {
        try {
          File file= new File(Objects.requireNonNull(OrginizationStructureApp.class.getClassLoader().getResource("employee.csv")).getFile());
            OrginizationStructure orginizationStructure = new OrginizationStructureImpl();
            List<Employee> employees= readAndParseCsv(file);
            orginizationStructure.addSubordinate(employees);
            orginizationStructure.checkSalaries(employees);
            orginizationStructure.checkReportingLines(employees);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
