package com.example.a21601861.cosport.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.a21601861.cosport.DATA.DataTest;
import com.example.a21601861.cosport.Listenner;
import com.example.a21601861.cosport.R;
import com.example.a21601861.cosport.UserPackage.User;
import com.example.a21601861.cosport.UserPackage.UserImp;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Locale;

public class ActivityView extends Listenner {
    private ActivityDesc act;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra("activityId")) {
            setContentView(R.layout.activity_view);
            this.act = ActivityDescImp.getActivityById(intent.getIntExtra("activityId", -1));
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
            if(this.act.haveUser(DataTest.currentUser.getId())) {
                ((Switch) findViewById(R.id.swParticipate)).setChecked(true);
            }
            addUserToList();
            this.addCreator();
        }
    }
    private void addUserToList(){
        TableLayout list=findViewById(R.id.ListUser);
        for (int idUser:this.act.getUserList() ) {
            User user= UserImp.findUserById(idUser);
            TableRow row=new TableRow(list.getContext());
            LinearLayout verLayout=new LinearLayout(row.getContext());
            verLayout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView userImage=new ImageView(verLayout.getContext());
            if(user.hasURLProfilPicture()){
                Picasso.with(verLayout.getContext()).load(user.getURLOfProfilPicture()).
                        resize(200, 200).into(userImage);
            }
            else {
                userImage.setImageResource(R.mipmap.tete);
            }
            userImage.setAdjustViewBounds(true);
            userImage.setMaxWidth(200);
            userImage.setMaxHeight(200);
            verLayout.addView(userImage);
            TextView name=new TextView(verLayout.getContext());
            name.setText(user.getName());
            verLayout.addView(name);
            row.addView(verLayout);
            list.addView(row);

        }
    }
    private void addCreator(){
        ((TextView)findViewById(R.id.Creator)).setText("Organisateur: "+UserImp.findUserById(act.getCreatorId()).getName());
        ((ImageView)findViewById(R.id.imageCreator)).setImageResource(UserImp.findUserById(act.getCreatorId()).getProfilPicture());
    }

    public void ClickOnSwitch(View view){
        if(this.act.haveUser(DataTest.currentUser.getId())){
            this.act.removeUser(DataTest.currentUser.getId());
        }
        else {
            this.act.addUser(DataTest.currentUser.getId());
        }
    }
}