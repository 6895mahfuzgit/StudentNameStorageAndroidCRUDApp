package com.game.android.mahfuzcse11.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by MahfuzCSE'11 on 22-Jun-17.
 */

public class MyReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        if (intent.getAction().equalsIgnoreCase("com.exp.BroadCast")) {

            String msg = bundle.getString("msg");


            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdusObj.length];
                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                    // SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String senderNum = messages[i].getOriginatingAddress();
                    String message = messages[i].getMessageBody();//

                }


            }
        }
    }
}