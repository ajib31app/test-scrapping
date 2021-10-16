package com.example;

import com.example.scraping.Scraping;

import java.io.IOException;

public class MainProgram {
    public static void main(String[] args) throws IOException {
        Scraping scraping = new Scraping();
        scraping.getLinkProductList();

        CSVWrite csvWrite = new CSVWrite();
        csvWrite.writeToCSV(scraping.getProductList(), "result1.csv");
        System.out.println("Process Finish");
        scraping.finish();
    }
}
