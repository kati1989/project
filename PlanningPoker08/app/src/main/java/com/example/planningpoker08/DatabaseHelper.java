package com.example.planningpoker08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planningpoker08.Group;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "planning_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create groups table
        db.execSQL(Group.CREATE_TABLE);
        db.execSQL(Question.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Group.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertGroup(String group) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Group.COLUMN_NAME, group);

        // insert row
        long id = db.insert(Group.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Group getGroup(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Group.TABLE_NAME,
                new String[]{Group.COLUMN_ID, Group.COLUMN_NAME},
                Group.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare group object
        Group group = new Group(
                cursor.getInt(cursor.getColumnIndex(Group.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Group.COLUMN_NAME))
                );

        // close the db connection
        cursor.close();

        return group;
    }

    public List<String> getAllGroups() {
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

        // updating row
        return db.update(Group.TABLE_NAME, values, Group.COLUMN_ID + " = ?",
                new String[]{String.valueOf(group.getId())});
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

    public List<String> getAllQuestions() {
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
                group = cursor.getString(cursor.getColumnIndex(Group.COLUMN_NAME));

                questions.add(group);
            } while (cursor.moveToNext());//atrakja a cursort  a kovtekezo elemre
        }

        // close db connection
        db.close();
        return questions;
    }

}