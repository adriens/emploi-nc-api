package com.github.adriens.emploi.nc.api.service;

import com.github.adriens.emploi.nc.sdk.CSVLine;
import com.github.adriens.emploi.nc.sdk.Emploi;
import com.github.adriens.emploi.nc.sdk.Emplois;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.ArrayList;

public class ReportService {

    public static ArrayList<CSVLine> getReportCSV(int numberLatest) throws IOException {
        return  Emplois.getCSVLines(numberLatest);
    }
}
