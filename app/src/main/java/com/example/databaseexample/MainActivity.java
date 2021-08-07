package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.databaseexample.Params.Question_Answer;
import static com.example.databaseexample.Params.Question_Detail;
import static com.example.databaseexample.Params.Question_KEY_ID;
import static com.example.databaseexample.Params.Question_Option1;
import static com.example.databaseexample.Params.Question_Option2;
import static com.example.databaseexample.Params.Question_Option3;
import static com.example.databaseexample.Params.Question_Option4;

/*
APP - DataBaseExample
Path D:\AndroidStudioProjects\RNS\DataBaseExample
Github Repository - DataBaseExample

This is SQL database example
This is Multiple choice Question type data base
This is Display Question list in logcat
Data is added/updated/deleted/listed in logcat only

Updated to add/Edit/Delete/record in main activity screen

 */
public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    EditText edittextView;
    EditText edittextView2;
    EditText edittextView3;
    EditText edittextView4;
    EditText edittextView5;
    EditText edittextView6;
    EditText edittextView7;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Ravi", "Start 1");
        edittextView = (EditText) findViewById(R.id.editquestionid);
        edittextView2 = (EditText) findViewById(R.id.editTextViewQ);
        edittextView3 = (EditText) findViewById(R.id.editTextViewOA);
        edittextView4 = (EditText) findViewById(R.id.editTextViewOB);
        edittextView5 = (EditText) findViewById(R.id.editTextViewOC);
        edittextView6 = (EditText) findViewById(R.id.editTextViewOD);
        edittextView7 = (EditText) findViewById(R.id.editTextViewAns);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        MyDbHandler db = new MyDbHandler(MainActivity.this);
        // all Question are displayed in list
        ArrayList questionList = db.getAllCotacts();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, questionList);
        Log.d("Ravi", "Start 2 no of Question in list are : " + questionList.size());

        // Question are clicked in the list is displayed in Question with Options
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "List Item is Clicked", Toast.LENGTH_SHORT).show();
                Log.d("Ravi", "MainActivity listView.setOnItemClickListener onItemClick() 1 list item clicked position " + position + " id = " + id);
                String selectedItem = (String) parent.getItemAtPosition(position);
                int Qid = Integer.parseInt(selectedItem.substring(0, selectedItem.indexOf(',', 0)));

                Log.d("Ravi", "MainActivity listView.setOnItemClickListener onItemClick() 1 list item clicked selected item " + selectedItem + " id = " + Qid + ", ki Position " +selectedItem.indexOf(',', 0) +" Extracted text " + selectedItem.substring(0, selectedItem.indexOf(',', 0)));
//                int Qid = listView.getCheckedItemPosition();
                Toast.makeText(MainActivity.this, "Qid " + Qid, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ", ki Position" + selectedItem.indexOf(',', 0), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Extracted text " + selectedItem.substring(0, selectedItem.indexOf(',', 0)), Toast.LENGTH_SHORT).show();
                if (Qid>=0){
                    Cursor rs = db.getData(Qid);
                    rs.moveToFirst();
                    Log.d("Ravi", "MainActivity listView.setOnItemClickListener onItemClick() 2 list item clicked " + position + " " + Qid);
                    if(rs.moveToFirst()){
                        Log.d("Ravi", "MainActivity listView.setOnItemClickListener onItemClick() 3 list item clicked  " + rs);
//                        edittextView.setClickable(false);
//                        edittextView.setFocusable(false);
                        edittextView.setText(rs.getString(rs.getColumnIndex(Params.Question_KEY_ID)));
                        edittextView2.setText(rs.getString(rs.getColumnIndex(Params.Question_Detail)));
                        edittextView3.setText(rs.getString(rs.getColumnIndex(Params.Question_Option1)));
                        edittextView4.setText(rs.getString(rs.getColumnIndex(Params.Question_Option2)));
                        edittextView5.setText(rs.getString(rs.getColumnIndex(Params.Question_Option3)));
                        edittextView6.setText(rs.getString(rs.getColumnIndex(Params.Question_Option4)));
                        edittextView7.setText(rs.getString(rs.getColumnIndex(Params.Question_Answer)));
                        Toast.makeText(MainActivity.this, "Display Clicked record", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(MainActivity.this, "End of File occurred ", Toast.LENGTH_SHORT).show();
                        Log.d("Ravi", "MainActivity listView.setOnItemClickListener onItemClick() 4 list item clicked nd of File occurred " + rs);
                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "List item position not found " + position, Toast.LENGTH_SHORT).show();
                    Log.d("Ravi", "MainActivity listView.setOnItemClickListener onItemClick() 5 list item clicked " + position + " " + id);
                }
                Log.d("Ravi", "MainActivity listView.setOnItemClickListener onItemClick() 6");
            }
        });

        // List Button is clicked
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "List Button Clicked", Toast.LENGTH_SHORT).show();
                Log.d("Ravi", "MainActivity list button4.setOnItemClickListener onClick() 1 ");
                int questionid = Integer.parseInt(edittextView.getText().toString());
                if (questionid <= 0) {
                    Log.d("Ravi", "MainActivity list button4.setOnItemClickListener onClick() 2 " + questionid);
                    Toast.makeText(MainActivity.this, "Enter Correct ID", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor rs = db.getData(questionid);
                    Log.d("Ravi", "MainActivity listView.setOnItemClickListener onClick() 3 list item clicked " + questionid + " Records " + db.getCount());
                    if (rs.moveToFirst()) {
                        edittextView.setText(rs.getString(rs.getColumnIndex(Question_KEY_ID)));
                        edittextView2.setText(rs.getString(rs.getColumnIndex(Params.Question_Detail)));
                        edittextView3.setText(rs.getString(rs.getColumnIndex(Params.Question_Option1)));
                        edittextView4.setText(rs.getString(rs.getColumnIndex(Params.Question_Option2)));
                        edittextView5.setText(rs.getString(rs.getColumnIndex(Params.Question_Option3)));
                        edittextView6.setText(rs.getString(rs.getColumnIndex(Params.Question_Option4)));
                        edittextView7.setText(rs.getString(rs.getColumnIndex(Params.Question_Answer)));
                        Log.d("Ravi", "MainActivity list button4.setOnItemClickListener onClick() 4 " + rs.getString(rs.getColumnIndex(Params.Question_KEY_ID)));
                    } else {
                        Log.d("Ravi", "MainActivity list button4.setOnItemClickListener onClick() 2 " + questionid);
                        Toast.makeText(MainActivity.this, "ID Record not found ", Toast.LENGTH_SHORT).show();
                        edittextView.setText("");
                        edittextView2.setText("");
                        edittextView3.setText("");
                        edittextView4.setText("");
                        edittextView5.setText("");
                        edittextView6.setText("");
                        edittextView7.setText("");
                    }
                }
            }
        });


//        Setting Text Color On Button Inside Java class:
//        Below is the example code in which we set the text color of a Button programmatically means in java class.
//        Button simpleButton=(Button) findViewById(R.id.simpleButton);
//        simpleButton.setTextColor(Color.RED);//set the red color for the text

//        Setting textSize In Java class:
//        Below is the example code in which we set the text size of a Button programmatically means in java class.
//        Button simpleButton=(Button)findViewById(R.id.simpleButton);
//        simpleButton.setTextSize(25);//set the text size of button

//        Setting background in Button In Java class:
//        Below is the example code in which we set the background color of a Button programmatically means in java class.
//        Button simpleButton=(Button)findViewById(R.id.simpleButton);
//        simpleButton.setBackgroundColor(Color.BLACK);//set the black color of button background

        // add
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add Button Clicked", Toast.LENGTH_SHORT).show();
                Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 1 ");

                Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 2 ");
                String Question = edittextView2.getText().toString();
                String OptionA = edittextView3.getText().toString();
                String OptionB = edittextView4.getText().toString();
                String OptionC = edittextView5.getText().toString();
                String OptionD = edittextView6.getText().toString();
                String Answer = edittextView7.getText().toString();
                Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 3 All entered filed are stored  ");
                if(Question.isEmpty() || OptionA.isEmpty()|| OptionB.isEmpty() || OptionC.isEmpty() || OptionD.isEmpty() || Answer.isEmpty())
                {
                    Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 4 Some filed are empty ");
                    Toast.makeText(MainActivity.this, "Enter All Field", Toast.LENGTH_SHORT).show();
                } else
                {
                    Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 5 Call insert record");
                    long id = db.insertData(Question,OptionA,OptionB,OptionC,OptionD,Answer);
                    if(id<=0)
                    {
                        Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 6 insert record fail " + id);
                        Toast.makeText(MainActivity.this, "Insertion Un-Successful", Toast.LENGTH_SHORT).show();

                    } else
                    {
                        Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 7 record inserted " + id);
                        Toast.makeText(MainActivity.this, "Insertion Successful", Toast.LENGTH_SHORT).show();
                        edittextView.setText("");
                        edittextView2.setText("");
                        edittextView3.setText("");
                        edittextView4.setText("");
                        edittextView5.setText("");
                        edittextView6.setText("");
                        edittextView7.setText("");
                        Log.d("Ravi", "MainActivity add button.setOnItemClickListener onClick() 8 filled become empty ");
                    }
                }
            }
        });

        // Update
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Update Button Clicked", Toast.LENGTH_SHORT).show();
                Log.d("Ravi", "MainActivity update button2.setOnClickListener onClick 1 ");
                String Qid = edittextView.getText().toString();
                String Question = edittextView2.getText().toString();
                String OptionA = edittextView3.getText().toString();
                String OptionB = edittextView4.getText().toString();
                String OptionC = edittextView5.getText().toString();
                String OptionD = edittextView6.getText().toString();
                String Answer = edittextView7.getText().toString();
                Log.d("Ravi", "MainActivity update button2.setOnClickListener onClick 2  All entered filed are stored ");
                if (Qid.isEmpty() || Question.isEmpty() || OptionA.isEmpty() || OptionB.isEmpty() || OptionC.isEmpty() || OptionD.isEmpty() || Answer.isEmpty()) {
                    Log.d("Ravi", "MainActivity update button2.setOnClickListener onClick 3 Same filed are empty ");
                    Toast.makeText(MainActivity.this, "Enter All Field", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Ravi", "MainActivity update button2.setOnClickListener onClick 4 Call Update record");
                    long RowUpdated = db.updateContact(Qid, Question, OptionA, OptionB, OptionC, OptionD, Answer);
                    if (RowUpdated <= 0) {
                        Log.d("Ravi", "MainActivity update button.setOnItemClickListener onClick 5 Update record fail " + Qid);
                        Toast.makeText(MainActivity.this, "Update Un-Successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("Ravi", "MainActivity update button.setOnItemClickListener onClick 6 record Updated " + RowUpdated + " Id = " + Qid);
                        Toast.makeText(MainActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                        edittextView.setText("");
                        edittextView2.setText("");
                        edittextView3.setText("");
                        edittextView4.setText("");
                        edittextView5.setText("");
                        edittextView6.setText("");
                        edittextView7.setText("");
                        Log.d("Ravi", "MainActivity update button.setOnItemClickListener onClick() 7 filed become empty ");
                    }
                }
            }
        });

        // Delete
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Delete Button Clicked", Toast.LENGTH_SHORT).show();
                Log.d("Ravi", " MainActivity delete button3.setOnClickListener onClick 1 ");
                String Qid = edittextView.getText().toString();
                int QIid = Integer.parseInt(Qid);
                Log.d("Ravi", "MainActivity delete button3.setOnClickListener onClick 2 ");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to Delete ?");
                builder.setTitle("Alert !");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        long RowDeleted = db.deleteContactById(QIid);
                        if (RowDeleted <= 0) {
                            Log.d("Ravi", "MainActivity delete button3.setOnItemClickListener onClick() 3 Record not delete " + QIid);
                            Toast.makeText(MainActivity.this, "Delete Un-Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("Ravi", "MainActivity delete button3.setOnItemClickListener onClick() 4 record deleted " + RowDeleted + " Id = " + Qid);
                            Toast.makeText(MainActivity.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                            edittextView.setText("");
                            edittextView2.setText("");
                            edittextView3.setText("");
                            edittextView4.setText("");
                            edittextView5.setText("");
                            edittextView6.setText("");
                            edittextView7.setText("");
                            Log.d("Ravi", "MainActivity delete button3.setOnItemClickListener onClick() 5 field become empty ");
                        }
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        Log.d("Ravi", "MainActivity delete button3.setOnClickListener onClick 7 Alert box open ");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Log.d("Ravi", "MainActivity delete button3.setOnClickListener onClick 7 Deletion Canceled ");
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Are you sure");
                alertDialog.show();
            }
        });

        // blank button is clicked
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Blank button is clicked", Toast.LENGTH_SHORT).show();
                Log.d("Ravi", "MainActivity Blank button5.setOnClickListener onClick ");
                edittextView.setText("");
                edittextView2.setText("");
                edittextView3.setText("");
                edittextView4.setText("");
                edittextView5.setText("");
                edittextView6.setText("");
                edittextView7.setText("");
                Log.d("Ravi", "MainActivity Blank button5.setOnClickListener onClick - all filed cleared ");
            }
        });




        // add record
//        // get one question
//        int Value = 1;
//        Log.d("Ravi", "Cursor Start 210 ");
//        Cursor rs = db.getData(1);
//        id_To_Update = Value;
////        rs.moveToFirst();
//        Log.d("Ravi", "Cursor Start 211 "+ rs);
//
//        textView.setText(rs.getString(rs.getColumnIndex(Params.Question_KEY_ID)));
//        Log.d("Ravi", " List View 212 " + Question_KEY_ID);
//        textView2.setText(rs.getString(rs.getColumnIndex(Params.Question_Detail)));
//        textView3.setText(rs.getString(rs.getColumnIndex(Params.Question_Option1)));
//        textView4.setText(rs.getString(rs.getColumnIndex(Params.Question_Option2)));
//        textView5.setText(rs.getString(rs.getColumnIndex(Params.Question_Option3)));
//        textView6.setText(rs.getString(rs.getColumnIndex(Params.Question_Option4)));
//        textView7.setText(rs.getString(rs.getColumnIndex(Params.Question_Answer)));
//        Log.d("Ravi", " List View 213 " + Question_Detail);

        // Creating a contact object for the db


        // delete contact by id
//        db.deleteContactById(2);
//        db.deleteContactById(3);
//        db.deleteContactById(4);
//        db.deleteContactById(6);
//        db.deleteContactById(7);
//        db.deleteContactById(8);
//        db.deleteContactById(9);
//        db.deleteContactById(10);
//        Log.d("Ravi", "Id for 17 successfully updated to the db");
//        db.deleteContactById(18);
//        Log.d("Ravi", "Id for 18 successfully updated to the db");
//        db.deleteContactById(19);
//        Log.d("Ravi", "Id for 19 successfully updated to the db");
//         this loop delete records from id 50 to 495 from data base
//        for(int i= 50; i<=495; i++){
//            db.deleteContactById(i);
//            Log.d("Ravi", "List Question \nId: " + question.getId() + "\n" +
//                    "Question : " + question.getQuestion_Detail() + "\n" +
//                    "Option 1: " + question.getQuestion_Option1() + "\n" +
//                    "Option 2: " + question.getQuestion_Option2() + "\n" +
//                    "Option 3: " + question.getQuestion_Option3() + "\n" +
//                    "Option 4: " + question.getQuestion_Option4() + "\n" +
//                    "Answer : " + question.getQuestion_Answer() + "\n" );
//        }

        // count question (i.e no of rows in database
        Log.d("Ravi", "Total rows by no of rows "+ db.numberOfRows()+ " in your database");
        Log.d("Ravi", "Total rows by get data "+ db.getData(12)+ " in your database");

        //means this is the view part not the add contact part.
        Log.d("Ravi", "MainActivity onCreate 3 Cursor last ");
        Cursor rs = db.getData(12);
        rs.moveToFirst();
        Log.d("Ravi", "MainActivity onCreate 4 " + rs);
        Log.d("Ravi", "MainActivity onCreate 5 " + rs.getString(rs.getColumnIndex(Question_KEY_ID)) + ", " + rs.getString(rs.getColumnIndex(Question_Detail)) + "\n");
        Log.d("Ravi", "MainActivity onCreate getData() 4 " + rs.getString(rs.getColumnIndex(Question_Option1)) + ", " + rs.getString(rs.getColumnIndex(Question_Option2)) + "\n");
        Log.d("Ravi", "MainActivity onCreate getData() 5 " + rs.getString(rs.getColumnIndex(Question_Option3)) + ", " + rs.getString(rs.getColumnIndex(Question_Option4)) + "\n");
    }

}