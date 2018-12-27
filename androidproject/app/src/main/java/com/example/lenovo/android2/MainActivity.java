package com.example.lenovo.android2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
private Spinner mySpinner;
private EditText number;
private EditText content;
private TextView Length;
    private ArrayAdapter<String> arr_adapter;
private int select ;
private Button b1;
private Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySpinner=findViewById(R.id.mySpinner);

        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                select=arg2;

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub   myTextView.setText("Nothing");
            }
        });
        Length=(TextView)findViewById(R.id.num1);
        b1 = (Button) findViewById(R.id.num3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open1(v,select);

            }
        });
        b2 = (Button) findViewById(R.id.num4);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open2(v,select);

            }
        });
    }
private void open1(View v,int select){
    number=findViewById(R.id.num2);
    content=findViewById(R.id.num5);
    BufferedWriter writer=null;
    String n1=number.getText().toString().trim();
    n1=n1+"txt";
    String n2=content.getText().toString().trim();
    int b=n2.length();
    String n3="写入的字符串长度为";
    n3=n3+Integer.toString(b)+"个";
    Length.setText(n3);
    FileOutputStream fos=null;
    try {
        if(select==0)
            fos=openFileOutput(n1,Context.MODE_PRIVATE);
        else if(select==1)
       fos=openFileOutput(n1,Context.MODE_APPEND);
        else if(select==2)
            fos=openFileOutput(n1,Context.MODE_WORLD_READABLE);
        else
            fos=openFileOutput(n1,Context.MODE_WORLD_WRITEABLE);
        writer=new BufferedWriter(new OutputStreamWriter(fos));
       writer.write(n2);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (writer != null) {
            try {
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

    private void open2(View v,int select){
        number=findViewById(R.id.num2);
        content=findViewById(R.id.num5);
        String n1=number.getText().toString().trim();
        n1=n1+"txt";
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder con=new StringBuilder();
        try {
          in=openFileInput(n1);
          reader=new BufferedReader(new InputStreamReader(in));
          String line="";
          while((line=reader.readLine())!=null) {
              con.append(line);
          }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader!= null) {
                try {
                    Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(); }}
        }
        int b=con.length();
        String n3="读取的字符串长度为";
        n3=n3+Integer.toString(b)+"个";
        Length.setText(n3);
        content.setText(con);

    }
}
