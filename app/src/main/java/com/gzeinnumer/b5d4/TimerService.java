package com.gzeinnumer.b5d4;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

import androidx.annotation.Nullable;

//todo 1 buat class TimerService
public class TimerService extends Service {

    public static final String BROADCAST_TIME_TIK_ACTION
            = "com.example.basicandroid.day6.notification.TimerService.BROADCAST_TIME_TIK_ACTION";
    public static final String BROADCAST_TIME_TIK_DATA
            = "com.example.basicandroid.day6.notification.TimerService.BROADCAST_TIME_TIK_DATA";

    private Intent intentTimeTik;
    private CountDownTimer countDownTimer;

    @Override
    public void onCreate() {
        super.onCreate();
        intentTimeTik = new Intent(BROADCAST_TIME_TIK_ACTION);
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long time = millisUntilFinished / 1000;
                intentTimeTik.putExtra(BROADCAST_TIME_TIK_DATA, time);
                sendBroadcast(intentTimeTik);
                //sentdata
                funSendData("");
            }
            @Override
            public void onFinish() {

                stopSelf();
            }
        };
        countDownTimer.start();
    }

    private void funSendData(String s) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
