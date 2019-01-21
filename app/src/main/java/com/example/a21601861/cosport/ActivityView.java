package com.example.a21601861.cosport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class ActivityView extends AppCompatActivity {
    private Transition slideTransition;
    private Scene scene_menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra("activityId")) {
            setContentView(R.layout.activity_view);
            ActivityDesc act = ActivityDescImp.getActivityById(intent.getIntExtra("activityId", -1));
            ((ImageView) findViewById(R.id.IconAct)).setImageResource(act.getIconId());
            ((TextView) findViewById(R.id.titleActView)).setText(act.getName());
            Calendar calAct = act.getDate();
            String hour;
            String minute;
            hour = "" + calAct.get(Calendar.HOUR_OF_DAY);
            minute = "" + calAct.get(Calendar.MINUTE);
            if (calAct.get(Calendar.HOUR_OF_DAY) < 10) {
                hour = "0" + calAct.get(Calendar.HOUR_OF_DAY);
            }
            if (calAct.get(Calendar.MINUTE) < 10) {
                minute = "0" + calAct.get(Calendar.MINUTE);
            }
            ((TextView) findViewById(R.id.hour)).setText("Heure: " + hour + "h" + minute);
            ((TextView) findViewById(R.id.date)).setText("Le " + calAct.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + " " + calAct.get(Calendar.DAY_OF_MONTH) + " " + calAct.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + calAct.get(Calendar.YEAR));

        }

        slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(1000);
        scene_menu = new Scene((ViewGroup) findViewById(R.id.menu));
    }

    public void onClickListenner(View view) {
        switch (view.getId()) {
            case R.id._icon_menu:
                TransitionManager.go(scene_menu, slideTransition);
                findViewById(R.id.menu).setVisibility(View.VISIBLE);
                break;

            case R.id.return_menu:
                TransitionManager.go(scene_menu, slideTransition);
                findViewById(R.id.menu).setVisibility(View.GONE);
                break;

        }
    }

    private void setParticipantList(ActivityDesc act){

    }
}