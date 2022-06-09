package com.gzeinnumer.b5d4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.gzeinnumer.b5d4.databinding.ActivityMainBinding;
import com.gzeinnumer.da.constant.ButtonStyle;
import com.gzeinnumer.da.dialog.confirmDialog.ConfirmDialog;

public class MainActivity extends AppCompatActivity {

    //asyntask
    //workmanager
    //broadcasr BroadcastReceiver

    //todo 2
    private BroadcastReceiver timeTikBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(TimerService.BROADCAST_TIME_TIK_ACTION)){
                long time = intent.getLongExtra(TimerService.BROADCAST_TIME_TIK_DATA,0);
                binding.tvTimer.setText(String.valueOf(time));
                
                //todo 7
                if(time==0){
                    displayDialog();
//                    displayDialogV2();
                }
            }
        }
    };

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //todo 2.1
        startService(new Intent(this, TimerService.class));

        binding.btnTrigger.setOnClickListener(view -> {
            startService(new Intent(this, TimerService.class));
        });
    }

    //todo 3
    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(timeTikBroadcastReceiver,
                new IntentFilter(TimerService.BROADCAST_TIME_TIK_ACTION));
    }

    //todo 4
    @Override
    public void onPause() {
        unregisterReceiver(timeTikBroadcastReceiver);
        super.onPause();
    }

    //todo 8
    private void displayDialog() {
        Toast.makeText(getApplicationContext(), "Show Dialog", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton("Oke Nih", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false);

        AlertDialog dialog = alert.create();
        dialog.show();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dialog.dismiss();
//            }
//        }, 2000);
    }

    //todo 9
    private void displayDialogV2() {
        new ConfirmDialog(getSupportFragmentManager())
            .setTitle("ini title")
            .setContent("ini content")
            .onCancelPressedCallBack(new ConfirmDialog.OnCancelPressed() {
                @Override
                public void onCancelPressed() {
                    Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                }
            })
            .onOkPressedCallBack(new ConfirmDialog.OnOkPressed() {
                @Override
                public void onOkPressed() {
                    Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                }
            })
            .show();
    }
}