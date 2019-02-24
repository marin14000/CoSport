package com.example.a21601861.cosport.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.a21601861.cosport.DATA.DataTest;
import com.example.a21601861.cosport.Listenner;
import com.example.a21601861.cosport.MainActivity;
import com.example.a21601861.cosport.R;

import java.util.Calendar;

public class CreateActivity extends Listenner {
    public Calendar cal=Calendar.getInstance();
    public String act="";
    public String place="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);

        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));

        ((Spinner)findViewById(R.id.view_spinner_act)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                act=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void listenner(View v) {
        switch (v.getId()){
            case R.id._view_calendar:
                DatePickerDialog dp=new DatePickerDialog(v.getContext());
                dp.updateDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
                dp.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int dayOfMonth) {
                        cal.set(y,m,dayOfMonth);
                    }
                });
                dp.show();
                break;
            case R.id._view_time_picker:
                TimePickerDialog tp=new TimePickerDialog(v.getContext(),new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),i,i1);
                    }
                },cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true);
                tp.show();
                break;

            case R.id.val_crea:
                place=((TextView)findViewById(R.id.place_text)).getText().toString();
                ActivityDesc ac=new ActivityDescImp(ActivityDescImp.getIcon(act),act,place,cal, DataTest.currentUser.getId());
                DataTest.activity.add(1,ac);
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
