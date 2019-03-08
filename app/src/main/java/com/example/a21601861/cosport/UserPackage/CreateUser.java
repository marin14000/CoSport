package com.example.a21601861.cosport.UserPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.a21601861.cosport.DATA.Data;
import com.example.a21601861.cosport.MainActivity;
import com.example.a21601861.cosport.R;

public class CreateUser extends AppCompatActivity {
    private static final UserBuilder userbuilder=new UserBuilder();
    @Override
    public void onCreate(Bundle savedbundle) {
        super.onCreate(savedbundle);
        setContentView(R.layout.adduser);

    }
    public void createUser(View view){
        userbuilder.setPrenom(((TextView)findViewById(R.id.prenom)).getText().toString());
        userbuilder.setNom(((TextView)findViewById(R.id.nom)).getText().toString());
        userbuilder.setLog(((TextView)findViewById(R.id.log)).getText().toString());
        userbuilder.setDesc(((TextView)findViewById(R.id.desc)).getText().toString());
        userbuilder.setMail(((TextView)findViewById(R.id.email)).getText().toString());
        userbuilder.setMdp(((TextView)findViewById(R.id.mdp)).getText().toString(),((TextView)findViewById(R.id.confmdp)).getText().toString());
        userbuilder.setAge(((TextView)findViewById(R.id.age)).getText().toString());
        if(((RadioButton)findViewById(R.id.h)).isChecked()){
            userbuilder.setGenre("h");
        }
        else if(((RadioButton)findViewById(R.id.f)).isChecked()){
            userbuilder.setGenre("f");
        }
        else if(((RadioButton)findViewById(R.id.na)).isChecked()){
            userbuilder.setGenre("nh");
        }

        if(userbuilder.isValid()){
            User u=userbuilder.build();
            Data.currentUser=u;
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            i.putExtra("log",u.getLog());
            startActivity(i);
        }
        else{
            Snackbar.make(view, userbuilder.getError(), Snackbar.LENGTH_LONG).show();
        }
    }
}
