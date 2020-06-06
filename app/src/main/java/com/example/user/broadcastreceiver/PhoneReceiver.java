package com.example.user.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneReceiver extends BroadcastReceiver {

    String Incoming, Outgoing;
    String sendIncomming, sendOutgoing;
    public static final String INCOMMING = "Incomming: ";
    public static final String OUTGOING = "Outgoing: ";


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction()
                .equals("android.intent.action.NEW_OUTGOING_CALL")) {
            String Outgoing = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            sendOutgoing = OUTGOING + Outgoing;
            Log.e("Phone", "Number: " + sendOutgoing);

        }

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);

            if (state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                Incoming = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                sendIncomming = INCOMMING + Incoming;
                Log.e("Phone", "Number: " + sendIncomming);
            }
        }

        while (Incoming != null || Outgoing != null) {
            Toast.makeText(context, "send it to server", Toast.LENGTH_LONG).show();
            Incoming = null;
            Outgoing = null;
        }
    }
}