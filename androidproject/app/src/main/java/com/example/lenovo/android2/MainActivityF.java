package com.example.lenovo.android2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityF extends AppCompatActivity {
private Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_f);
        b1 = (Button) findViewById(R.id.num1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open1(v);

            }
        });
        b2 = (Button) findViewById(R.id.num2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open2(v);

            }
        });
        b3= (Button) findViewById(R.id.num3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open3(v);

            }
        });
    }
    void open1(View v)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    void open2(View v)
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    void open3(View v)
    {
        Intent intent=new Intent(this,Main3Activity.class);
        startActivity(intent);

    }
}
