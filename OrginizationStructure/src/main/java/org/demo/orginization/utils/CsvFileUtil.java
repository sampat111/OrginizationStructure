package org.demo.orginization.utils;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.demo.orginization.domain.Employee;

import java.io.*;
import java.util.List;

public class CsvFileUtil {

    public static List<Employee> readAndParseCsv(File fileName) throws FileNotFoundException {
        Reader reader = new BufferedReader(new FileReader(fileName));
        CsvToBean csvReader = new CsvToBeanBuilder(reader)
                .withType(Employee.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        return csvReader.parse();
    }
}
