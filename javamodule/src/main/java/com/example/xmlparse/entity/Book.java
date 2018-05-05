package com.example.xmlparse.entity;

/**
 * Desc
 * Created by Michael on 2018/4/24.
 */

public class Book {
    private String counts;
    private String startTime;
    private String endTime;
    private String success;
    private String failure;
    private String downUrl;
    //set and get method
    public String getCounts() {
        return counts;
    }
    public void setCounts(String counts) {
        this.counts = counts;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public String getFailure() {
        return failure;
    }
    public void setFailure(String failure) {
        this.failure = failure;
    }
    public String getDownUrl() {
        return downUrl;
    }
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }
}
