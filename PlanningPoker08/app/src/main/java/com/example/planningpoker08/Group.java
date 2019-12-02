package com.example.planningpoker08;

public class Group {

    public static final String TABLE_NAME = "groupTable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ACTIVE_QUESTION = "questionId";


    private int id;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    private int questionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group(int id, String name, int questionId) {
        this.id = id;
        this.name = name;
        this.questionId=questionId;
    }

    @Override
    public String toString() {
        return getName();
    }


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_ACTIVE_QUESTION+ " INTEGER "
                    + ")";
}
