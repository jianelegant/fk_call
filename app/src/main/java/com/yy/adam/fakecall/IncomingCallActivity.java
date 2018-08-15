package com.yy.adam.fakecall;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class IncomingCallActivity extends AppCompatActivity {

    ImageView mLeftArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.dismissKeyguard(this);
        setContentView(R.layout.activity_incoming_call);
        Util.hideSystemNaviUI(this);

        initViews();
    }

    private void initViews() {
        mLeftArrow = findViewById(R.id.id_left_arrow);
        ObjectAnimator animation = ObjectAnimator.ofFloat(mLeftArrow, "translationX", 100f);
        animation.setDuration(1000);
        animation.start();
    }

    public static void callStart(Activity activity) {
        if(null != activity) {
            activity.startActivity(new Intent(activity, IncomingCallActivity.class));
        }
    }
}
