package com.example.ngoan.sqlite_sugarorm.jobsmodel;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

/**
 * Created by Ngoan on 04/11/2017.
 */

@Table(name = "Jobs_title")
public class Jobs extends SugarRecord{

    @Column(name = "job_title")
    private String jobTitle;


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
