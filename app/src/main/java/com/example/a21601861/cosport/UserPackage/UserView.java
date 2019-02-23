package com.example.a21601861.cosport.UserPackage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a21601861.cosport.Listenner;
import com.example.a21601861.cosport.R;

public class UserView extends Listenner {

    @Override
    public void onCreate(Bundle sv) {
        super.onCreate(sv);
        Intent i=getIntent();
        if(i.hasExtra("UserId")){
            setContentView(R.layout.userview);
            User u=UserImp.findUserById(i.getIntExtra("UserId",-1));
            ((TextView)findViewById(R.id.pseudostxt)).setText(u.getName());
            ((TextView)findViewById(R.id.prenomtxt)).setText(u.getPrenom());
            ((TextView)findViewById(R.id.nomtxt)).setText(u.getNom());
            ((TextView)findViewById(R.id.agetxt)).setText(u.getAge()+" Ans");
            ((TextView)findViewById(R.id.genretxt)).setText(UserImp.decode(u.getGenre()));
            ((TextView)findViewById(R.id.mailtxt)).setText(u.getMail());
            ((TextView)findViewById(R.id.descrtxt)).setText(u.getDescription());
        }
    }
}
