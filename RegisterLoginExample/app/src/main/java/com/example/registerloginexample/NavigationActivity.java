package com.example.registerloginexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NavigationActivity extends AppCompatActivity {
    private Button btn_navi;
    private EditText et_start_la, et_start_lo, et_end_la, et_end_lo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        et_start_la = findViewById(R.id.et_start_la);
        et_start_lo = findViewById(R.id.et_start_lo);
        et_end_la = findViewById(R.id.et_end_la);
        et_end_lo = findViewById(R.id.et_end_lo);
        btn_navi = findViewById(R.id.btn_navi);

        btn_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String start_la = et_start_la.getText().toString();
                String start_lo = et_start_lo.getText().toString();
                String end_la = et_end_la.getText().toString();
                String end_lo = et_end_lo.getText().toString();
                double la1=Double.valueOf(start_la);
                double lo1=Double.valueOf(start_lo);
                double la2=Double.valueOf(end_la);
                double lo2=Double.valueOf(end_lo);

                Toast.makeText(getApplicationContext(),"경로 탐색을 시작합니다.",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                intent.putExtra("start_la", la1);
                intent.putExtra("start_lo", lo1);
                intent.putExtra("end_la", la2);
                intent.putExtra("end_lo", lo2);
                startActivity(intent);


            }
        });


    }
}