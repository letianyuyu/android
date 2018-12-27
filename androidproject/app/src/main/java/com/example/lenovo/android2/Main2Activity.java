package com.example.lenovo.android2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.example.lenovo.android2.PermisionUtils.verifyStoragePermissions;

public class Main2Activity extends AppCompatActivity {

    private Button b1;
    private Button b2,b3;
    private EditText R1,R2,wenjianming;
    private TextView con,toa,cond;
    private String r1,r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        con=(TextView)findViewById(R.id.con);
        cond=(TextView)findViewById(R.id.cont);
        b1 = (Button) findViewById(R.id.n1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open1(v);

            }
        });
        b2 = (Button) findViewById(R.id.n2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open2(v);

            }
        });
        b3 = (Button) findViewById(R.id.n3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open3(v);

            }
        });
    }
    public static double getNum(int startNum,int endNum){
        startNum=startNum*1000000;endNum=endNum*1000000;
        if(endNum > startNum){
            Random random = new Random();
            return (double)(random.nextInt(endNum - startNum) + startNum)/1000000.0;
        }
        return 0;
    }
    void open1(View v)
    {
      R1=(EditText)findViewById(R.id.num5);
      R2=(EditText)findViewById(R.id.num7);
       r1=R1.getText().toString().trim();
       r2=R2.getText().toString().trim();
        int start= Integer.valueOf(r1).intValue();
        int end=Integer.valueOf(r2).intValue();
        String content="";
        for(int i=0;i<10;i++) {
            double num = getNum(start, end);
            content=content+Double.toString(num)+'\n';
        }
        con.setText(content);
    }
   void open3(View v)
   {
       wenjianming=(EditText)findViewById(R.id.num3);
       String wenjianname=wenjianming.getText().toString().trim();
       toa=(TextView)findViewById(R.id.num2);
       String toas="已将"+wenjianname+"文件从SD卡中读出";
       toa.setText(toas);
       String state= Environment.getExternalStorageState();
       if(state.equals(Environment.MEDIA_MOUNTED))
       {

           File filepath=Environment.getExternalStorageDirectory();

           wenjianname=wenjianname+"txt";

           File file=new File(filepath,wenjianname);

           try{
               verifyStoragePermissions(this);
              FileInputStream fos=new FileInputStream(file);
              byte[] buffer=new byte[fos.available()];
              fos.read(buffer);
              String s=new String(buffer);
              cond.setText(s);
               Toast.makeText(this,"读取成功",Toast.LENGTH_SHORT).show();
               fos.close();

           }catch (FileNotFoundException e){
               Toast.makeText(this,"读取失败",Toast.LENGTH_SHORT).show();

           }
           catch (IOException e){}
       }

   }

    void open2(View v)
    {
       String content=con.getText().toString().trim();
       wenjianming=(EditText)findViewById(R.id.num3);
       String wenjianname=wenjianming.getText().toString().trim();
       toa=(TextView)findViewById(R.id.num2);
       String toas="已将"+r1+"至"+r2+"的随机数写入"+wenjianname+"文件并写入SD卡";
       toa.setText(toas);
    String state= Environment.getExternalStorageState();
    if(state.equals(Environment.MEDIA_MOUNTED))
    { File filepath=Environment.getExternalStorageDirectory();
        wenjianname=wenjianname+"txt";
        File file=new File(filepath,wenjianname);
        try{
            verifyStoragePermissions(this);
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(content.getBytes());
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            fos.close();

        }catch (FileNotFoundException e){
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();

        }
        catch (IOException e){}
    }
    }
}
