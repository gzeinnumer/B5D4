package com.gzeinnumer.b5d4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.gzeinnumer.b5d4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver timeTikBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(TimerService.BROADCAST_TIME_TIK_ACTION)){
                long time = intent.getLongExtra(TimerService.BROADCAST_TIME_TIK_DATA,0);
                binding.tvTimer.setText(String.valueOf(time));
            }
        }
    };

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startService(new Intent(this, TimerService.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(timeTikBroadcastReceiver,
                new IntentFilter(TimerService.BROADCAST_TIME_TIK_ACTION));
    }

    @Override
    public void onPause() {
        unregisterReceiver(timeTikBroadcastReceiver);
        super.onPause();
    }

}