package com.example.englishtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private EditText edtid;
    private EditText edtpass;
    private Button btconfirm;
    private Button btexit;
    private Button btregistration;
    Database database;
    private ArrayList<Player> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtid = (EditText) findViewById(R.id.editTextLogIn);
        edtpass = (EditText) findViewById(R.id.editTextPass);
        btconfirm = (Button) findViewById(R.id.buttonConfirm);
        btexit = (Button) findViewById(R.id.buttonExit);
        btregistration = (Button) findViewById(R.id.buttonRegistration);

        database = new Database(this, "dataplayer.sqlite", null, 1);

        database.queryData("CREATE TABLE IF NOT EXISTS Player(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR, TenDN VARCHAR, MatKhau VARCHAR, Diem1 INTEGER, Diem2 INTEGER, Diem3 INTEGER, Diem4 INTEGER, Diem5 INTEGER)");

        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() == 0) {
                    Toast.makeText(Login.this, R.string.textdangnhapthatbai, Toast.LENGTH_LONG).show();
                }
                for (int i=0; i<list.size(); i++) {
                    if (edtid.getText().toString().equals(list.get(i).getUsername()) && edtpass.getText().toString().equals(list.get(i).getPass())) {
                        Toast.makeText(Login.this, R.string.textdangnhapthanhcong, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Option.class);
                        intent.putExtra("id", list.get(i).getId());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, R.string.textdangnhapthatbai, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        Cursor datadangnhap = database.getData("SELECT * FROM Player");
        list.clear();
        while (datadangnhap.moveToNext()) {
            String ten = datadangnhap.getString(1);
            String tendn = datadangnhap.getString(2);
            String matkhau = datadangnhap.getString(3);
            int id = datadangnhap.getInt(0);
            int diem1 = datadangnhap.getInt(4);
            int diem2 = datadangnhap.getInt(5);
            int diem3 = datadangnhap.getInt(6);
            int diem4 = datadangnhap.getInt(7);
            int diem5 = datadangnhap.getInt(8);
            list.add(new Player(id, ten, tendn, matkhau, diem1, diem2, diem3, diem4, diem5));
        }
    }
}