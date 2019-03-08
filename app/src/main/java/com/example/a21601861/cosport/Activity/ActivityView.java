package com.example.a21601861.cosport.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.a21601861.cosport.DATA.Data;
import com.example.a21601861.cosport.Listenner;
import com.example.a21601861.cosport.MainActivity;
import com.example.a21601861.cosport.R;
import com.example.a21601861.cosport.UserPackage.User;
import com.example.a21601861.cosport.UserPackage.UserImp;
import com.example.a21601861.cosport.UserPackage.UserView;
import com.example.a21601861.cosport.http.DataStorageAccess;
import com.example.a21601861.cosport.http.Result;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class ActivityView extends Listenner {
    private ActivityDesc act;
    private HashMap<Integer,User> rowLinkUser=new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra("activityId")) {
            setContentView(R.layout.activity_view);
//            this.act = ActivityDescImp.getActivityById(intent.getIntExtra("activityId", -1));
            DataStorageAccess dsa=DataStorageAccess.getInstance();
            dsa.getActivity(intent.getIntExtra("activityId", -1));
            this.act=dsa.getResult().getAct();

            dsa.getListUserOfAct(this.act.getId());

            for(User u:dsa.getResult().getListOfUser()){
                this.act.addUser(u);
            }

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
            ((TextView) findViewById(R.id.hour)).setText("Heure : " + hour + "h" + minute);
            ((TextView) findViewById(R.id.date)).setText("Le " + calAct.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + " " + calAct.get(Calendar.DAY_OF_MONTH) + " " + calAct.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + calAct.get(Calendar.YEAR));
            ((TextView)findViewById(R.id.place)).setText("A : "+act.getPlace());
            if(this.act.haveUser(Data.currentUser.getId())) {
                ((Switch) findViewById(R.id.swParticipate)).setChecked(true);
            }
            addUserToList();
            this.addCreator();
        }
    }
    private void addUserToList(){
        TableLayout list=findViewById(R.id.ListUser);
        for (User user:this.act.getUserList() ) {


            TableRow row=new TableRow(list.getContext());
            row.setId(View.generateViewId());
            rowLinkUser.put(row.getId(),user);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(), UserView.class);
                    i.putExtra("UserId",rowLinkUser.get(view.getId()).getId());
                    startActivity(i);
                }
            });
            LinearLayout verLayout=new LinearLayout(row.getContext());
            verLayout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView userImage=new ImageView(verLayout.getContext());
            setUserProfilImage(userImage,verLayout.getContext(),user);
            userImage.setAdjustViewBounds(true);
            userImage.setMaxWidth(200);
            userImage.setMaxHeight(200);
            verLayout.addView(userImage);
            TextView name=new TextView(verLayout.getContext());
            name.setText(user.getLog());
            verLayout.addView(name);
            row.addView(verLayout);
            list.addView(row);


        }
    }
    private void addCreator(){
        ((TextView)findViewById(R.id.Creator)).setText("Organisateur: "+act.getCreator().getLog());
        setUserProfilImage(((ImageView)findViewById(R.id.imageCreator)),getApplicationContext(),act.getCreator());
        findViewById(R.id.creatorLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),UserView.class);
                i.putExtra("UserId",act.getCreator().getId());
                startActivity(i);
            }
        });
    }

    public void ClickOnSwitch(View view){
        if(this.act.haveUser(Data.currentUser.getId())){
            this.act.removeUser(Data.currentUser.getId());
            DataStorageAccess dsa=DataStorageAccess.getInstance();
            dsa.informUserRemove(Data.currentUser.getId(),this.act.getId());
        }
        else {
            this.act.addUser(Data.currentUser);
            DataStorageAccess dsa=DataStorageAccess.getInstance();
            dsa.informUserJoin(Data.currentUser.getId(),this.act.getId());
        }
        this.refrechUserList();
    }

    private void refrechUserList() {
        ViewGroup v=findViewById(R.id.ListUser);
        v.removeAllViews();
        this.addUserToList();
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}