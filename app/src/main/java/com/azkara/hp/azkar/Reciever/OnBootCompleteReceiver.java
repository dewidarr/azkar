package com.azkara.hp.azkar.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.azkara.hp.azkar.Util.GeneralMethods;

public class OnBootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        GeneralMethods.Init_Alarm(context);
    }
}
