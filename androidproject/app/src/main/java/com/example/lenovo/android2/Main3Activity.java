package com.example.lenovo.android2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
private SharedPreferences sp;
private Button b1;
private CheckBox log;
private EditText name,age,height,weight,school,class1,apart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
      sp=this.getSharedPreferences("info", Context.MODE_PRIVATE);
        b1 = (Button) findViewById(R.id.dengru);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        height=(EditText)findViewById(R.id.height);
        weight=(EditText)findViewById(R.id.weight);
        school=(EditText)findViewById(R.id.school);
        apart=(EditText)findViewById(R.id.apart);
        class1=(EditText)findViewById(R.id.class1);
        log=(CheckBox)findViewById(R.id.log);
        name.setText(sp.getString("Name",""));
        age.setText(sp.getString("Age",""));
        height.setText(sp.getString("Height",""));
        weight.setText(sp.getString("Weight",""));
        school.setText(sp.getString("School",""));
        class1.setText(sp.getString("Class1",""));
        apart.setText(sp.getString("Apart",""));
        log.setChecked(sp.getBoolean("ischecked",false));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog();
                open1(v);

            }
        });


    }
    private void showDialog1(){
        new AlertDialog.Builder(this)
                .setTitle("出现错误")
                .setMessage("姓名不合法")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("取消",null)
                .setNegativeButton("确定",null)
                .create()
                .show();

    }
    private void showDialog2(){
        new AlertDialog.Builder(this)
                .setTitle("出现错误")
                .setMessage("年龄不合法")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("取消",null)
                .setNegativeButton("确定",null)
                .create()
                .show();

    }
    void open1(View v)
    {
        String Name=name.getText().toString().trim();
        String Age=age.getText().toString().trim();
        String Height=height.getText().toString().trim();
        String Weight=weight.getText().toString().trim();
        String School=school.getText().toString().trim();
        String Class1=class1.getText().toString().trim();
        String Apart=apart.getText().toString().trim();
        for(int i=0;i<Name.length();i++){
            char x=Name.charAt(i);
            if(x>='0' &&x<='9'  ){
                showDialog1();return;
            }
        }
        int A=Integer.valueOf(Age).intValue();
        if(A>200||A<0)
        {
            showDialog2();return;
        }
        if(log.isChecked())
        { Log.i("Main3Activity","记住密码");
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("Name",Name);
            editor.putString("Age",Age);
            editor.putString("Height",Height);
            editor.putString("Weight",Weight);
            editor.putString("School",School);
            editor.putString("Class1",Class1);
            editor.putString("Apart",Apart);
            editor.putBoolean("ischecked",true);
            editor.commit();
            Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
        }
        else {
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("Name",null);
            editor.putString("Age",null);
            editor.putString("Height",null);
            editor.putString("Weight",null);
            editor.putString("School",null);
            editor.putString("Class1",null);
            editor.putString("Apart",null);
            editor.putBoolean("ischecked",false);
            editor.commit();
            Toast.makeText(this,"登入但不保存信息",Toast.LENGTH_LONG).show(); }
    }

}
