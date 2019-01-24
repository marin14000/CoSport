package com.example.a21601861.cosport;

import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class Listenner extends AppCompatActivity {

    private static final Slide slideTransition;
    private Scene scene_menu;
    static{
        slideTransition=new Slide(Gravity.RIGHT);
        slideTransition.setDuration(1000);}

    public void onClickOnMenu(View view){
        if(scene_menu==null){
            scene_menu=new Scene((ViewGroup)findViewById(R.id.menu));
        }
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
}
