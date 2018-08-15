package com.yy.adam.fakecall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class IncomingCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.dismissKeyguard(this);
        setContentView(R.layout.activity_incoming_call);
        Util.hideSystemNaviUI(this);
    }

    public static void callStart(Activity activity) {
        if(null != activity) {
            activity.startActivity(new Intent(activity, IncomingCallActivity.class));
        }
    }
}
