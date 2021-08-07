package com.example.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.databaseexample.Params.Question_Answer;
import static com.example.databaseexample.Params.Question_Detail;
import static com.example.databaseexample.Params.Question_KEY_ID;
import static com.example.databaseexample.Params.Question_Option1;
import static com.example.databaseexample.Params.Question_Option2;
import static com.example.databaseexample.Params.Question_Option3;
import static com.example.databaseexample.Params.Question_Option4;


public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.d("Ravi", "on create ");
//        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);
//        Log.d("Ravi", "on create drop table ");
//        SQLiteDatabase.deleteDatabase(new File(Params.DB_NAME));
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.Question_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Params.Question_Detail + " TEXT, "
                + Params.Question_Option1 + " TEXT, "
                + Params.Question_Option2 + " TEXT, "
                + Params.Question_Option3 + " TEXT, "
                + Params.Question_Option4 + " TEXT, "
                + Params.Question_Answer + " INTEGER "
                + ")";
        Log.d("Ravi", "Query being run is : " + create);
        db.execSQL(create);
        Log.d("Ravi", "Create table  : ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getData(int id) {
        Log.d("Ravi", "DBHelper Cursor Start 47 " + id );
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + Params.TABLE_NAME + " where id="+id+"", null );
        Log.d("Ravi", "DBHelper Cursor Start 48 " + res);
        return res;
    }

    public Cursor getData() {
        Log.d("Ravi", "DBHelper Cursor Start 47 ");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + Params.TABLE_NAME, null );
        Log.d("Ravi", "DBHelper Cursor Start 48 " + res);
        return res;

    }

    // insert data
    public long insertData(String Question, String OptionA, String OptionB, String OptionC, String OptionD, String Answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.Question_Detail, Question);
        contentValues.put(Params.Question_Option1, OptionA);
        contentValues.put(Params.Question_Option2, OptionB);
        contentValues.put(Params.Question_Option3, OptionC);
        contentValues.put(Params.Question_Option4, OptionD);
        contentValues.put(Question_Answer, Answer);
        long id = db.insert(Params.TABLE_NAME, null , contentValues);
        return id;
    }

    // add question
    public void addContact(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues questionValues = new ContentValues();
//        questionValues.put(Params.Question_KEY_ID, question.getId());
        questionValues.put(Params.Question_Detail, question.getQuestion_Detail());
        questionValues.put(Params.Question_Option1, question.getQuestion_Option1());
        questionValues.put(Params.Question_Option2, question.getQuestion_Option2());
        questionValues.put(Params.Question_Option3, question.getQuestion_Option3());
        questionValues.put(Params.Question_Option4, question.getQuestion_Option4());
        questionValues.put(Question_Answer, question.getQuestion_Answer());


        db.insert(Params.TABLE_NAME, null, questionValues);
        Log.d("Ravi", "addContact : Successfully inserted " + questionValues);
        db.close();
        Log.d("Ravi", "addContact : Successfully close ");

    }

    // list questions
    public List<Question> getAllContacts() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if (cursor.moveToFirst()) {
            Log.d("Ravi", "List<Question> : Successfully Listed ");
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setQuestion_Detail(cursor.getString(cursor.getColumnIndex(Params.Question_Detail)));
                question.setQuestion_Option1(cursor.getString(2));
                question.setQuestion_Option2(cursor.getString(3));
                question.setQuestion_Option3(cursor.getString(4));
                question.setQuestion_Option4(cursor.getString(5));
                question.setQuestion_Answer(cursor.getString(6));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        Log.d("Ravi", "List<Question> : Successfully Listed and return  ");
        return questionList;
    }

    // Update Question
    public int updateContact(String Qid, String Question, String OptionA, String OptionB, String OptionC, String OptionD, String Answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues questionValues = new ContentValues();
        questionValues.put(Params.Question_KEY_ID, Qid);
        questionValues.put(Params.Question_Detail, Question);
        questionValues.put(Params.Question_Option1, OptionA);
        questionValues.put(Params.Question_Option2, OptionB);
        questionValues.put(Params.Question_Option3, OptionC);
        questionValues.put(Params.Question_Option4, OptionD);
        questionValues.put(Question_Answer, Answer);

        //Lets update now
        Log.d("Ravi", "updateContact : Successfully updated " + questionValues);

        int RowsUpdated = db.update(Params.TABLE_NAME, questionValues, Params.Question_KEY_ID + "=?",
                new String[]{String.valueOf(Qid)});
        return RowsUpdated;
    }

    // delete question by id
    public int deleteContactById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
//        int RowsDeleted = db.delete(Params.TABLE_NAME, Params.Question_KEY_ID + "=?", new String[]{String.valueOf(id)});
        int RowsDeleted = db.delete(Params.TABLE_NAME, Params.Question_KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return RowsDeleted;
    }

    // delete question by id
    public void deleteContact(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.Question_KEY_ID + "=?", new String[]{String.valueOf(question.getId())});
        db.close();
    }

    // count number of rows (question) in your database
    public int getCount() {
        String query = "SELECT  * FROM " + Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    // count number of rows (question) in your database
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("Ravi", "DBHelper numberOfRows Start 49 ");
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Params.TABLE_NAME );
        Log.d("Ravi", "DBHelper numberOfRows Start 50 " + numRows);
        return numRows;
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> questionList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if (cursor.moveToFirst()) {
            Log.d("Ravi", "List<Question> : Successfully Listed 1");
            do {
//                Question question = new Question();
//                question.setId(Integer.parseInt(cursor.getString(0)));
//                question.setQuestion_Detail(cursor.getString(1));
//                questionlist.cursor.getString(cursor.getColumnIndex(Params.Question_Detail));
//                question.setQuestion_Option1(cursor.getString(2));
//                question.setQuestion_Option2(cursor.getString(3));
//                question.setQuestion_Option3(cursor.getString(4));
//                question.setQuestion_Option4(cursor.getString(5));
//                question.setQuestion_Answer(cursor.getString(6));
//                questionList.add(question);
                questionList.add(cursor.getString(cursor.getColumnIndex(Params.Question_KEY_ID)) + ", " + cursor.getString(cursor.getColumnIndex(Params.Question_Detail)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option1)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option2)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option3)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option4)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Answer)));
                Log.d("Ravi", "MainActivity onCreate 5 " + cursor.getString(cursor.getColumnIndex(Question_KEY_ID)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Detail)) + "\n");
                Log.d("Ravi", "MainActivity onCreate getData() 4 " + cursor.getString(cursor.getColumnIndex(Question_Option1)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option2)) + "\n");
                Log.d("Ravi", "MainActivity onCreate getData() 5 " + cursor.getString(cursor.getColumnIndex(Question_Option3)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option4)) + "\n");

            } while (cursor.moveToNext());
            Log.d("Ravi", "List<Question> getAllCotacts : : Successfully Listed 2");
        }
        Log.d("Ravi", "List<Question> getAllCotacts : Successfully Listed and return questionlist 3");
        return questionList;
    }

    public ArrayList<Question> getAllQuestion() {
        Log.d("Ravi", "ArrayList<Question> getAllQuestion() function start ");
        ArrayList<Question> questionList = new ArrayList<Question>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        Log.d("Ravi", "ArrayList<Question> getAllQuestion() Prepared Query");
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Log.d("Ravi", "ArrayList<Question> getAllQuestion() run Query");
        Cursor cursor = db.rawQuery(select, null);
        Log.d("Ravi", "ArrayList<Question> getAllQuestion() Query completed");

        //Loop through now
        if (cursor.moveToFirst()) {
            Log.d("Ravi", "ArrayList<Question> getAllQuestion() moveToFirst - Query record found");
            do {
                Log.d("Ravi", "ArrayList<Question> getAllQuestion() added all question one by one");
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setQuestion_Detail(cursor.getString(1));
//                question.cursor.getString(cursor.getColumnIndex(Params.Question_Detail));
                question.setQuestion_Option1(cursor.getString(2));
                question.setQuestion_Option2(cursor.getString(3));
                question.setQuestion_Option3(cursor.getString(4));
                question.setQuestion_Option4(cursor.getString(5));
                question.setQuestion_Answer(cursor.getString(6));
                questionList.add(question);
                Log.d("Ravi", "ArrayList<Question> getAllQuestion() added all question one by one " + questionList);
//                questionList.add(cursor.getString(cursor.getColumnIndex(Params.Question_KEY_ID)) + ", " + cursor.getString(cursor.getColumnIndex(Params.Question_Detail)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option1)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option2)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option3)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option4)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Answer)));
                Log.d("Ravi", "MainActivity onCreate 5 " + cursor.getString(cursor.getColumnIndex(Question_KEY_ID)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Detail)) + "\n");
                Log.d("Ravi", "MainActivity onCreate getData() 4 " + cursor.getString(cursor.getColumnIndex(Question_Option1)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option2)) + "\n");
                Log.d("Ravi", "MainActivity onCreate getData() 5 " + cursor.getString(cursor.getColumnIndex(Question_Option3)) + ", " + cursor.getString(cursor.getColumnIndex(Question_Option4)) + "\n");
            } while (cursor.moveToNext());
            Log.d("Ravi", "ArrayList<Question> getAllQuestion() : Successfully  added to cursor ");
        } else {
            Log.d("Ravi", "List<Question> getAllCotacts : no record found ");
        }

        Log.d("Ravi", "List<Question> getAllCotacts : return questionlist 3");
        return questionList;
    }
}
