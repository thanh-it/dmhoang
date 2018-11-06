package duan.thanhit.lab2mob201;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras!=null){
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String phoneNumber =extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context,phoneNumber,Toast.LENGTH_LONG).show();
            }
        }
    }
}
