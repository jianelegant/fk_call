package com.yy.adam.fakecall;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class IncomingCallActivity extends AppCompatActivity {

    private static final int HANDLER_MSG_ANIM = 1;
    private static final int HANDLER_ANIM_DUR = 1000;
    private Handler mHandler;

    View mAnswerCall;
    ImageView mLeftArrow;
    ObjectAnimator mLeftAnima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.dismissKeyguard(this);
        setContentView(R.layout.activity_incoming_call);
        Util.hideSystemNaviUI(this);
        initHandler();
        initViews();
    }

    private void initHandler() {
        mHandler = new Handler(this);
    }

    private void initViews() {
        mAnswerCall = findViewById(R.id.answer_call);
        mAnswerCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mLeftArrow = findViewById(R.id.id_left_arrow);
        startAnim();
    }

    private void startAnim() {
        if(mHandler.hasMessages(HANDLER_MSG_ANIM)) {
            mHandler.removeMessages(HANDLER_MSG_ANIM);
        }
        loopAnim();
    }

    private void loopAnim() {
        if(null == mLeftAnima) {
            mLeftAnima = ObjectAnimator.ofFloat(mLeftArrow, "translationX", 0, 100f, 0);
            mLeftAnima.setDuration(HANDLER_ANIM_DUR);
        }
        mLeftAnima.start();
        mHandler.sendEmptyMessageDelayed(HANDLER_MSG_ANIM, HANDLER_ANIM_DUR);
    }

    public static void callStart(Activity activity) {
        if(null != activity) {
            activity.startActivity(new Intent(activity, IncomingCallActivity.class));
        }
    }

    static class Handler extends android.os.Handler {
        WeakReference<IncomingCallActivity> activityWeakReference;

        public Handler(IncomingCallActivity activity) {
            super();
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_MSG_ANIM:
                    if(null != activityWeakReference.get()) {
                        activityWeakReference.get().loopAnim();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
