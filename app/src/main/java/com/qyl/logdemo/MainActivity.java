package com.qyl.logdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qyl.log.LogUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.testBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.v("onClick");
                for (int i = 0; i < 100; i++){
                    LogUtil.v("click" +i, ""+i);
                }
            }
        });

        LogUtil.v("oncrate");
        LogUtil.v("MainActivity", "v MainActivity oncreate");

        LogUtil.v("qqq","qqqqqqqqqqqqq");
        LogUtil.v("www", "wwwwwwwwwwww");

        LogUtil.v(TAG, "v onCreate");
        LogUtil.v(TAG, "v onCreate 2", new Exception());
        LogUtil.v("verbose onCreate");

        LogUtil.d(TAG, "d oncreate 1");
        LogUtil.d(TAG, "d 2", new Exception("d2"));
        LogUtil.d("onCreate");

        LogUtil.i(TAG, "i ");
        LogUtil.i(TAG, "i 2", new Exception("i2"));
        LogUtil.i("info 1");
        LogUtil.i("info 2", new Exception("info 2"));

        LogUtil.w(TAG, "onCreate");
        LogUtil.w(TAG, "onCreate", new Exception("create"));
        LogUtil.w(TAG, new Exception("warn 2"));
        LogUtil.w("warining oncaret 1");

        LogUtil.e(TAG, "e 1");
        LogUtil.e(TAG, "e 2", new Exception("e2"));
        LogUtil.e("error1");
        LogUtil.e("error2", new Exception("error2"));

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
