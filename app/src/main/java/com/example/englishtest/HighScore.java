package com.example.englishtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {

    private TextView txthighscore1, txthighscore2, txthighscore3, txthighscore4, txthighscore5;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        txthighscore1 = (TextView) findViewById(R.id.textViewHighScore1);
        txthighscore2 = (TextView) findViewById(R.id.textViewHighScore2);
        txthighscore3 = (TextView) findViewById(R.id.textViewHighScore3);
        txthighscore4 = (TextView) findViewById(R.id.textViewHighScore4);
        txthighscore5 = (TextView) findViewById(R.id.textViewHighScore5);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        database = new Database(this, "dataplayer.sqlite", null, 1);

        Cursor cursor = database.getData("SELECT Diem1, Diem2, Diem3, Diem4, Diem5 FROM Player WHERE Id = " + id);
        if (cursor.moveToNext()) {
            txthighscore1.append(" " + cursor.getInt(0));
            txthighscore2.append(" " + cursor.getInt(1));
            txthighscore3.append(" " + cursor.getInt(2));
            txthighscore4.append(" " + cursor.getInt(3));
            txthighscore5.append(" " + cursor.getInt(4));
        }
    }
}