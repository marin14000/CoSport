package com.example.a21601861.cosport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21601861.cosport.DATA.Data;
import com.example.a21601861.cosport.UserPackage.CreateUser;
import com.example.a21601861.cosport.UserPackage.User;
import com.example.a21601861.cosport.http.DataStorageAccess;
import com.example.a21601861.cosport.http.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AuthActivity extends AppCompatActivity {
    private static File fiUserAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);
        if(AuthActivity.fiUserAcc==null){
            AuthActivity.fiUserAcc=new File(getFilesDir()+"/account.txt");

            AuthActivity.fiUserAcc.setWritable(true);
            AuthActivity.fiUserAcc.setReadable(true,true);
            System.out.println(getFilesDir());
        }
        if(AuthActivity.fiUserAcc.exists()) {
            String log = read();
            System.out.println("log= "+log);
            if(!log.equals("error") && !log.equals("")) {
                System.out.println("log: " + log);
                DataStorageAccess dsa = DataStorageAccess.getInstance();
                dsa.resarchUser(log);
                Result r = dsa.getResult();
                System.out.println("first dsa access");
                dsa.getUser(r.getIdOfUser());
                r = dsa.getResult();
                User u = r.getUser();

                Data.currentUser = u;

                start(log);
            }
        }

//        }
    }

    private String read() {
        String log="";
        AuthActivity.fiUserAcc.setReadable(true,true);
        try {
            FileReader fr=new FileReader(AuthActivity.fiUserAcc);
            int x=fr.read();
            while (x!=-1){
                log+=String.valueOf(x);
                x=fr.read();
            }
            return log;
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        return "error";
    }

    private void start(String log) {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("log",log);
        startActivity(i);
    }

    public void val_click(View view){
        if(view.getId()==R.id.val_login) {
            String mdp = ((TextView) findViewById(R.id.mdp)).getText().toString();
            String log = ((TextView) findViewById(R.id.login)).getText().toString();

            DataStorageAccess dsa=DataStorageAccess.getInstance();
            dsa.resarchUser(log);
            Result r=dsa.getResult();

            dsa.getUser(r.getIdOfUser());
            r=dsa.getResult();
            User u=r.getUser();

            if(u.getMDP().equals(mdp)){
                this.write(u.getLog());
                Data.currentUser=u;
                this.start(u.getLog());
            }
            else{
                Toast.makeText(getApplicationContext(), "Non connecté", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId()==R.id.val_crea){
            Intent i=new Intent(this.getApplicationContext(),CreateUser.class);
            startActivity(i);
        }
    }
    private void write(String toWrite){
        AuthActivity.fiUserAcc.setWritable(true,true);
        try {
            FileWriter fw=new FileWriter(AuthActivity.fiUserAcc);
            fw.write(toWrite);
        } catch (IOException e) {
            e.printStackTrace();}
        String x=read();
    }

    public static void deco(Context c){
        Intent i=new Intent(c,AuthActivity.class);
        if(AuthActivity.fiUserAcc.exists()) {
            AuthActivity.fiUserAcc.delete();
        }
        c.startActivity(i);
    }
    @Override
    public void onBackPressed(){
        System.out.println("Back Pressed");
        moveTaskToBack(true);
    }
}
