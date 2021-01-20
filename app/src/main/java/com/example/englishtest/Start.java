package com.example.englishtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Start extends AppCompatActivity {

    private ArrayList<CauHoi> listcauhoi;
    private TextView txtask, txta, txtb, txtc, txtd, txttime, txtpoint;
    private String da;
    private int index=0, point=0, time;
    private ProgressBar progressbar;
    private Database database;
    private int id;
    ArrayList<String> l = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        txtask = (TextView) findViewById(R.id.textViewAsk);
        txta = (TextView) findViewById(R.id.textViewA);
        txtb = (TextView) findViewById(R.id.textViewB);
        txtc = (TextView) findViewById(R.id.textViewC);
        txtd = (TextView) findViewById(R.id.textViewD);
        txttime = (TextView) findViewById(R.id.textViewTime);
        txtpoint = (TextView) findViewById(R.id.textViewPoint);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        listcauhoi = new ArrayList<>();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        this.id = id;

        database = new Database(this, "dataplayer.sqlite", null, 1);

        readJSON("http://olala5.atwebpages.com/data/data.php");

        txta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txta.getText().equals(da)) {
                    point+=5;
                    txtpoint.setText("Điểm: " + point);
                }
                nextQuestion();
            }
        });

        txtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtb.getText().equals(da)) {
                    point+=5;
                    txtpoint.setText("Điểm: " + point);
                }
                nextQuestion();
            }
        });

        txtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtc.getText().equals(da)) {
                    point+=5;
                    txtpoint.setText("Điểm: " + point);
                }
                nextQuestion();
            }
        });

        txtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtd.getText().equals(da)) {
                    point+=5;
                    txtpoint.setText("Điểm: " + point);
                }
                nextQuestion();
            }
        });
    }

    private void readJSON(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        listcauhoi.add(new CauHoi(object.getInt("_id"),
                                object.getString("_cauhoi"),
                                object.getString("_daa"),
                                object.getString("_dab"),
                                object.getString("_dac"),
                                object.getString("_dad"),
                                object.getString("_datrue")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Collections.shuffle(listcauhoi);
                showQuestion();
                thoiGian();
                progressbar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Start.this, error.toString(), Toast.LENGTH_LONG).show();
                progressbar.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void nextQuestion() {
        countDownTimer.cancel();
        index++;
        if (index < 20) {
            showQuestion();
            thoiGian();
        } else {
            txta.setEnabled(false);
            txtb.setEnabled(false);
            txtc.setEnabled(false);
            txtd.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    updatePoint();
                    dialogFinish();
                }
            }, 500);
        }
    }

    final CountDownTimer countDownTimer = new CountDownTimer(15000, 1000) {
        @Override
        public void onTick(long l) {
            time--;
            txttime.setText("Thời gian: " + time);
        }

        @Override
        public void onFinish() {
            nextQuestion();
        }
    };

    private void thoiGian() {
        time=15;
        txttime.setText("Thời gian: " + time);
        countDownTimer.start();
    }

    private void dialogFinish() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_finish);
        dialog.setCanceledOnTouchOutside(false);
        Button btconfirm = (Button) dialog.findViewById(R.id.buttonConfirmFinish);
        Button btexit = (Button) dialog.findViewById(R.id.buttonExitFinish);
        TextView txtfinish = (TextView) dialog.findViewById(R.id.textViewFinish);
        TextView txtfinishpoint = (TextView) dialog.findViewById(R.id.textViewFinishPoint);
        txtfinishpoint.setText(point+"");
        if (point<50) {
            txtfinish.setText(R.string.low);
        } else if (50<=point && point<100) {
            txtfinish.setText(R.string.mid);
        } else {
            txtfinish.setText(R.string.high);
        }
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(listcauhoi);
                index=0;
                point=0;
                txtpoint.setText("Điểm: " + point);
                showQuestion();
                thoiGian();
                txta.setEnabled(true);
                txtb.setEnabled(true);
                txtc.setEnabled(true);
                txtd.setEnabled(true);
                dialog.cancel();
            }
        });
        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                Start.this.finish();
            }
        });
        dialog.show();
    }

    private void showQuestion() {
        l.clear();
        l.add(listcauhoi.get(index).getDaa());
        l.add(listcauhoi.get(index).getDab());
        l.add(listcauhoi.get(index).getDac());
        l.add(listcauhoi.get(index).getDad());
        Collections.shuffle(l);
        txtask.setText(listcauhoi.get(index).getHoi());
        txta.setText(l.get(0));
        txtb.setText(l.get(1));
        txtc.setText(l.get(2));
        txtd.setText(l.get(3));
        da = listcauhoi.get(index).getDatrue();
    }

    private void updatePoint() {
        Cursor cursor = database.getData("SELECT Diem1, Diem2, Diem3, Diem4, Diem5 FROM Player WHERE Id = " + id);
        if (cursor.moveToNext()) {
            int d1, d2, d3, d4, d5;
            d1 = cursor.getInt(0);
            d2 = cursor.getInt(1);
            d3 = cursor.getInt(2);
            d4 = cursor.getInt(3);
            d5 = cursor.getInt(4);
            if (point > d1) {
                database.queryData("UPDATE Player SET Diem5 = " + d4 + ", Diem4 = " + d3 + ", Diem3 = " + d2 + ", Diem2 = " + d1 + ", Diem1 = " + point + " WHERE Id = '" + id + "'");
            } else if (point > d2) {
                database.queryData("UPDATE Player SET Diem5 = " + d4 + ", Diem4 = " + d3 + ", Diem3 = " + d2 + ", Diem2 = " + point + " WHERE Id = '" + id + "'");
            } else if (point > d3) {
                database.queryData("UPDATE Player SET Diem5 = " + d4 + ", Diem4 = " + d3 + ", Diem3 = " + point + " WHERE Id = '" + id + "'");
            } else if (point > d4) {
                database.queryData("UPDATE Player SET Diem5 = " + d4 + ", Diem4 = " + point + " WHERE Id = '" + id + "'");
            } else if (point > d5) {
                database.queryData("UPDATE Player SET Diem5 = " + point + " WHERE Id = '" + id + "'");
            }
        }
    }
}