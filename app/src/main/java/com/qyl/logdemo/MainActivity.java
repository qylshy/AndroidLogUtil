package com.qyl.logdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qyl.log.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.testBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.v("onClick");
            }
        });

        LogUtil.v("oncrate");
        LogUtil.v("MainActivity","v MainActivity oncreate");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    LogUtil.d("new Thread");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
