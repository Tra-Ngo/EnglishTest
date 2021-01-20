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

public class Registration extends AppCompatActivity {
    private String username, pass, fullname;
    private EditText edtusername, edtpass, edtfullname;
    private Button confirm;
    private ArrayList<Player> list;
    private Database database;
    private Boolean c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        database = new Database(this, "dataplayer.sqlite", null, 1);

        edtfullname = (EditText) findViewById(R.id.editTextFullName);
        edtusername = (EditText) findViewById(R.id.editTextRegistration);
        edtpass = (EditText) findViewById(R.id.editTextPassR);
        confirm = (Button) findViewById(R.id.buttonConfirmR);
        list = new ArrayList<>();

        getData();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtusername.getText().toString();
                pass = edtpass.getText().toString();
                fullname = edtfullname.getText().toString();
                boolean c = false;

                if (username.equals("")) {
                    Toast.makeText(Registration.this, "Bạn chưa nhập tên đăng nhập", Toast.LENGTH_LONG).show();
                } else if (pass.equals("")) {
                    Toast.makeText(Registration.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_LONG).show();
                } else if (fullname.equals("")) {
                    Toast.makeText(Registration.this, "Bạn chưa nhập họ và tên", Toast.LENGTH_LONG).show();
                } else {
                    for (int i=0; i<list.size(); i++) {
                        if (username.equals(list.get(i).getUsername())) {
                            Toast.makeText(Registration.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_LONG).show();
                            c = true;
                            break;
                        }
                    }
                    if (c == false) {
                        database.queryData("INSERT INTO Player VALUES(null, " + "'" + fullname + "', " + "'" + username + "', " + "'" + pass + "', " + "0, 0, 0, 0, 0" + ")");
                        finish();
                    }
                }
            }
        });
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