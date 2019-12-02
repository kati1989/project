package com.example.planningpoker08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 9;

    // Database Name
    private static final String DATABASE_NAME = "planningPoker_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create groups table
        db.execSQL(Group.CREATE_TABLE);
        db.execSQL(Question.CREATE_TABLE);
        db.execSQL(User.CREATE_TABLE);

        db.execSQL(Answer.CREATE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Group.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Question.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Answer.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUser(String name) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(User.COLUMN_NAME, name);

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }




    public User getUser(String name) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME, User.COLUMN_GROUP},
                User.COLUMN_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare group object
        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_GROUP)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME))
        );

        // close the db connection
        cursor.close();

        return user;
    }

    public User getUser(int id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME, User.COLUMN_GROUP},
                User.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare group object
        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_GROUP)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME))
        );

        // close the db connection
        cursor.close();

        return user;
    }

    public Group getGroup(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Group.TABLE_NAME,
                new String[]{Group.COLUMN_ID, Group.COLUMN_NAME, Group.COLUMN_ACTIVE_QUESTION},
                Group.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare group object
        Group group = new Group(
                cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Group.COLUMN_NAME)),
                cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ACTIVE_QUESTION))
        );

        // close the db connection
        cursor.close();

        return group;
    }


    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Group.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // az elso elemre allitja a cursort
        if (cursor.moveToFirst()) {
            do {
                String name;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                name = cursor.getString(cursor.getColumnIndex(Group.COLUMN_NAME));

                int id;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                id = cursor.getInt(cursor.getColumnIndex(Group.COLUMN_NAME));

                int activeQuestion;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                activeQuestion = cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ACTIVE_QUESTION));

                groups.add(new Group(id,name,activeQuestion));
            } while (cursor.moveToNext());//atrakja a cursort  a kovtekezo elemre
        }

        // close db connection
        db.close();
        return groups;
    }

    public List<String> getAllGroupsText() {
        List<String> groups = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Group.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // az elso elemre allitja a cursort
        if (cursor.moveToFirst()) {
            do {
                String group;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                group = cursor.getString(cursor.getColumnIndex(Group.COLUMN_NAME));

                groups.add(group);
            } while (cursor.moveToNext());//atrakja a cursort  a kovtekezo elemre
        }

        // close db connection
        db.close();
        return groups;
    }

    public int getGroupsCount() {
        String countQuery = "SELECT  * FROM " + Group.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        
        return count;
    }

    public int updateGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Group.COLUMN_NAME, group.getName());
        values.put(Group.COLUMN_ACTIVE_QUESTION, group.getQuestionId());


        // updating row
        return db.update(Group.TABLE_NAME, values, Group.COLUMN_ID + " = ?",
                new String[]{String.valueOf(group.getId())});
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, user.getName());
        values.put(User.COLUMN_GROUP, user.getGroupId());

        // updating row
        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getUserId())});
    }


    public void deleteGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Group.TABLE_NAME, Group.COLUMN_ID + " = ?",
                new String[]{String.valueOf(group.getId())});
        db.close();
    }

    public long insertQuestion(int groupid, String description, String sarttime, String endtime) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Question.COLUMN_GROUPID, groupid);
        values.put(Question.COLUMN_DESCRIPTION, description);
        values.put(Question.COLUMN_STARTTIME, sarttime);
        values.put(Question.COLUMN_ENDTIME, endtime);
        // insert row
        long id = db.insert(Question.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Question getQuestion(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Question.TABLE_NAME,
                new String[]{Question.COLUMN_ID, Question.COLUMN_GROUPID, Question.COLUMN_DESCRIPTION, Question.COLUMN_STARTTIME, Question.COLUMN_ENDTIME},
                Question.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare group object
        Question question = new Question(
                cursor.getInt(cursor.getColumnIndex(Question.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(Question.COLUMN_GROUPID)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_STARTTIME)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_ENDTIME)
        ));

        // close the db connection
        cursor.close();

        return question;
    }

    public List<String> getAllQuestionsText() {
        List<String> questions = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Question.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // az elso elemre allitja a cursort
        if (cursor.moveToFirst()) {
            do {
                String group;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                group = cursor.getString(cursor.getColumnIndex(Question.COLUMN_DESCRIPTION));

                questions.add(group);
            } while (cursor.moveToNext());//atrakja a cursort  a kovtekezo elemre
        }

        // close db connection
        db.close();
        return questions;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Question.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // az elso elemre allitja a cursort
        if (cursor.moveToFirst()) {
            do {
                int id;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                id = cursor.getInt (cursor.getColumnIndex(Question.COLUMN_ID));

                int groupId;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                groupId = cursor.getInt (cursor.getColumnIndex(Question.COLUMN_GROUPID));

                String description;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                description = cursor.getString(cursor.getColumnIndex(Question.COLUMN_DESCRIPTION));

                String startTime;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                startTime = cursor.getString(cursor.getColumnIndex(Question.COLUMN_STARTTIME));

                String endTime;// = new Group();
                //group.setId(cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)));
                endTime = cursor.getString(cursor.getColumnIndex(Question.COLUMN_ENDTIME));

                questions.add(new Question(id,groupId,description,startTime,endTime));
            } while (cursor.moveToNext());//atrakja a cursort  a kovtekezo elemre
        }

        // close db connection
        db.close();
        return questions;
    }

    public long insertGroup(String group) {
        // get writable database as we want to write data

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        List<Group> allGroup = getAllGroups();
        int max=0;
        if (!allGroup.isEmpty()) {
            Group maxId = allGroup.stream().max((item1, item2) -> {
                return item1.getId() - item2.getId();
            }).get();

            if (maxId != null)
                max = maxId.getId() + 1;
        }
        values.put(Group.COLUMN_ID,max );
        values.put(Group.COLUMN_NAME, group);
        values.put(Group.COLUMN_ACTIVE_QUESTION, 0);

        SQLiteDatabase db = this.getWritableDatabase();

        // insert row
        long id = db.insert(Group.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public long insertAnswer(int userId, int questionId, int vote, int group) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        values.put(Answer.COLUMN_USERID, userId);
        values.put(Answer.COLUMN_QUESTIONID, questionId);
        values.put(Answer.COLUMN_NOTE, vote);
        values.put(Answer.COLUMN_GROUPID, group);

        // insert row
        long id = db.insert(Answer.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<Answer> getAllQuestionsForGroup(int group) {
        List<Answer> questions = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Answer.TABLE_NAME + " WHERE " + Answer.COLUMN_GROUPID + "=" + group ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // az elso elemre allitja a cursort
        if (cursor.moveToFirst()) {
            do {
                int userId;// = new Group();
                userId = cursor.getInt (cursor.getColumnIndex(Answer.COLUMN_USERID));

                int groupId;// = new Group();
                groupId = cursor.getInt (cursor.getColumnIndex(Answer.COLUMN_GROUPID));

                int questionId;// = new Group();
                questionId = cursor.getInt(cursor.getColumnIndex(Answer.COLUMN_QUESTIONID));

                int note;// = new Group();
                note = cursor.getInt(cursor.getColumnIndex(Answer.COLUMN_NOTE));
            } while (cursor.moveToNext());//atrakja a cursort  a kovtekezo elemre
        }

        // close db connection
        db.close();
        return questions;
    }

    public List<Answer> getAllAnswers() {
        List<Answer> questions = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Answer.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // az elso elemre allitja a cursort
        if (cursor.moveToFirst()) {
            do {
                int userId;// = new Group();
                userId = cursor.getInt (cursor.getColumnIndex(Answer.COLUMN_USERID));

                int groupId;// = new Group();
                groupId = cursor.getInt (cursor.getColumnIndex(Answer.COLUMN_GROUPID));

                int questionId;// = new Group();
                questionId = cursor.getInt(cursor.getColumnIndex(Answer.COLUMN_QUESTIONID));

                int note;// = new Group();
                note = cursor.getInt(cursor.getColumnIndex(Answer.COLUMN_NOTE));
            } while (cursor.moveToNext());//atrakja a cursort  a kovtekezo elemre
        }

        // close db connection
        db.close();
        return questions;
    }
}