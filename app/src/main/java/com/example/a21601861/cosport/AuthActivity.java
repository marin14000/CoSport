package com.example.a21601861.cosport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AuthActivity extends AppCompatActivity {
    private static File fiUserAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(fiUserAcc==null){
            AuthActivity.fiUserAcc=new File(getFilesDir()+"account.txt");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);
        if(fiUserAcc.exists()){
            String log = read();
            start(log);
        }
    }

    private String read() {
        fiUserAcc.setReadable(true,true);
        String log="";
        try {
            FileReader fr=new FileReader(fiUserAcc);
            int x=fr.read();
            while (x!=-1){
                log+=String.valueOf(x);
                x=fr.read();
            }
        fiUserAcc.setReadable(false,false);
        return log;
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        fiUserAcc.setReadable(false,false);
        return "";
    }

    private void start(String log) {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("log",log);
        startActivity(i);
    }

    public void val_click(View view){
        String mdp= ((TextView)findViewById(R.id.mdp)).getText().toString();
        String log= ((TextView)findViewById(R.id.login)).getText().toString();
        if(log.equals("admin")){
            if(mdp.equals("0123456789")){
                this.write(log);
                this.start(log);
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Non connecté", Toast.LENGTH_SHORT).show();
        }

    }
    private void write(String toWrite){
        fiUserAcc.setWritable(true);
        try {
            FileWriter fw=new FileWriter(fiUserAcc);
            fw.write(toWrite);
        } catch (IOException e) {}
        fiUserAcc.setWritable(false);
    }

    public static void deco(Context c){
        Intent i=new Intent(c,AuthActivity.class);
        fiUserAcc.delete();
        c.startActivity(i);
    }
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
