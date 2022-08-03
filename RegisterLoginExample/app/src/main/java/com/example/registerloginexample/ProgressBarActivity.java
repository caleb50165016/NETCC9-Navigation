package com.example.registerloginexample;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity
{
    ProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        Button btnLoad = findViewById(R.id.btnload);
        Button btnmain = findViewById(R.id.btnmain);
        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProgressBarActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

        //로딩창 객체 생성
        customProgressDialog = new ProgressDialog(this);
        //로딩창을 투명하게
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        btnLoad.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 로딩창 보여주기
                customProgressDialog.show();
            }
        });
    }
}