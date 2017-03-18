package com.treadcommunicationdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnStartThread;
    private Button mBtnStopThread;
    private TextView mTvCounter;
    int counter = 0;
    private Handler handler;
    private boolean mIsThreadRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnStartThread = (Button) findViewById(R.id.btn_start_counter);
        mBtnStopThread = (Button) findViewById(R.id.btn_stop_counter);
        mTvCounter = (TextView) findViewById(R.id.tv_thread_counter);
        mBtnStartThread.setOnClickListener(this);
        mBtnStopThread.setOnClickListener(this);
        handler = new Handler(getApplicationContext().getMainLooper());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_counter:
                mIsThreadRunning = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mIsThreadRunning) {
                            try {
                                Thread.sleep(100);
                                counter++;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mTvCounter.setText("" + counter);
                                }
                            });
                        }
                    }
                }).start();

                break;
            case R.id.btn_stop_counter:
                mIsThreadRunning = false;
                break;
        }

    }
}
