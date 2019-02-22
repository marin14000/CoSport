package com.example.a21601861.cosport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a21601861.cosport.Activity.CreateActivity;
import com.example.a21601861.cosport.UserPackage.User;
import com.squareup.picasso.Picasso;

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

            case R.id.deco:
                AuthActivity.deco(getApplicationContext());
                break;

            case R.id.act_purpose:
                Intent i=new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(i);
                break;
        }

    }
    public void setUserProfilImage(ImageView imageView, Context context, User user){
        if(user.hasURLProfilPicture()){
            Picasso.with(context).load(user.getURLOfProfilPicture()).resize(200,200).into(imageView);
        }
        else {
            imageView.setImageResource(user.getProfilPicture());
        }
    }


    public void setUserName(User u, TextView textView){
        textView.setText(u.getName());
    }
}
