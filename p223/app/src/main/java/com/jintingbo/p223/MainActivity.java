package com.jintingbo.p223;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edit=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=findViewById(R.id.editText);
        Button btn1 = findViewById(R.id.button2);
        Button btn2 = findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取edit数据
                String str=edit.getText().toString().trim();
                //保存
                save(str);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取edit数据
                String dedaoString = load();
                edit.setText(dedaoString);
            }
        });
    }

    public void save(String str){

        FileOutputStream fos=null;
        BufferedWriter bw=null;
        try{
            fos=openFileOutput("bobo.txt",MODE_PRIVATE);
            bw=new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(str);
        }catch (IOException e){
        }finally {
            try{
                if(bw!=null){
                    bw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private String load(){
        FileInputStream in=null;
        BufferedReader br=null;
        StringBuilder sb=new StringBuilder();
        try{
            String line="";
            in=openFileInput("bobo.txt");
            br=new BufferedReader(new InputStreamReader(in));
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(br!=null){
                    br.close();
                }
            }catch (IOException e){

            }
        }
        return sb.toString();
    }

}
