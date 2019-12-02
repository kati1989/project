package com.example.planningpoker08;

public class Answer {
    public static final String TABLE_NAME = "answerTable";
    public static final String COLUMN_USERID = "userId";
    public static final String COLUMN_QUESTIONID = "questionId";
    public static final String COLUMN_GROUPID = "groupId";

    public static final String COLUMN_NOTE = "note";

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Answer(int userId, int groupId, int questionId, int note) {
        this.userId = userId;
        this.groupId = groupId;
        this.questionId = questionId;
        this.note = note;
    }

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_USERID + " INTEGER, "
                    + COLUMN_QUESTIONID + " INTEGER, "
                    + COLUMN_NOTE+ " INTEGER, "
                    + COLUMN_GROUPID+ " INTEGER "
                    + ")";
    private int userId;

    private int groupId;

    public int getUserId() {
        return userId;
    }

    public Answer(int userId, int questionId, int note) {
        this.userId = userId;
        this.questionId = questionId;
        this.note = note;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    private int questionId;
    private int note;
}
