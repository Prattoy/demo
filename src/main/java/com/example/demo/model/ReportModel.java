package com.example.demo.model;

import lombok.Data;

import javax.sql.DataSource;

@Data
public class ReportModel {
    private String reportCode;
    private String registrationNo;
    private String jesperName;
    private String viewType;
    private String reportType;
    private DataSource dataSource;

}
