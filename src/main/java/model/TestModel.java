package model;

import lombok.Data;
import java.util.Date;

@Data
public class TestModel {
    private String project;
    private String test;
    private Long minWorkingTime;
    private Date date;
    private int testCount;
    private int browserUsageAmount;
}
