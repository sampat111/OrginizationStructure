package org.demo.orginization;

import org.demo.orginization.domain.Employee;
import org.demo.orginization.services.OrginizationStructureImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.demo.orginization.utils.CsvFileUtil.readAndParseCsv;
import static org.junit.Assert.assertEquals;

public class OrginizationStructureTest {

    private OrginizationStructureImpl structure;
    private List<Employee> employees;

    @Before
    public void setup() throws FileNotFoundException {
        File file= new File(Objects.requireNonNull(OrginizationStructureTest.class.getClassLoader().getResource("employee.csv")).getFile());
        structure=new OrginizationStructureImpl();
        employees= readAndParseCsv(file);
        structure.addSubordinate(employees);
    }

    @Test
    public void checkReportingLinesTest(){
        OrginizationStructureImpl structure = new OrginizationStructureImpl();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        structure.checkReportingLines(employees);
        String expectedOutput = "Employee sam trof (ID: 307) has  1 extra managers between them and the CEO." ;

        assertEquals(expectedOutput, outputStream.toString());

    }

    @Test
    public void checkSalariesTest(){
        OrginizationStructureImpl structure = new OrginizationStructureImpl();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        structure.checkSalaries(employees);
        String expectedOutput = "Manager Martin Chekov (ID: 124) earns 15000.00 less than they should.\n"+
                "Manager Brett Hardleaf (ID: 305) earns 10400.00 less than they should.\n"+
                "Manager sam trof (ID: 306) earns 7400.00 less than they should.\n";

        assertEquals(expectedOutput, outputStream.toString());

    }
}
