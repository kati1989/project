package com.example.planningpoker08;

import java.util.Date;

public class Question {

    public static final String TABLE_NAME = "question";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GROUPID = "groupid";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STARTTIME = "starttime";
    public static final String COLUMN_ENDTIME = "endtime";


    public static final String COLUMN_NAME = "";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_GROUPID + " INTEGER, "
                    + COLUMN_DESCRIPTION + " TEXT, "
                    + COLUMN_STARTTIME + " TEXT, "
                    + COLUMN_ENDTIME + " TEXT, "
                    + ")";

    private int id;
    private int groupId;
    private String description;
    private String startTime;
    private String endTime;

    public Question(int id, int groupId, String description, String startTime, String endTime) {
        this.id = id;
        this.groupId = groupId;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
