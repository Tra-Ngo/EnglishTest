package com.example.englishtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Option extends AppCompatActivity {

    Button btstart, bttutorial, btinfo, btexit, bthighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        btstart = (Button) findViewById(R.id.buttonStart);
        bttutorial = (Button) findViewById(R.id.buttonTutorial);
        btinfo = (Button) findViewById(R.id.buttonInfo);
        btexit = (Button) findViewById(R.id.buttonExitOption);
        bthighscore = (Button) findViewById(R.id.buttonHighScore);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", -1);

        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        bttutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, Tutorial.class);
                startActivity(intent);
            }
        });

        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, Start.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        btinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Option.this, Info.class));
            }
        });

        bthighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, HighScore.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}