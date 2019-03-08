package com.example.a21601861.cosport;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.a21601861.cosport.Activity.ActivityDesc;
import com.example.a21601861.cosport.Activity.ActivityDescImp;
import com.example.a21601861.cosport.Activity.ActivityView;
import com.example.a21601861.cosport.DATA.Data;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends com.example.a21601861.cosport.Listenner{

    private final static Calendar calendar=Calendar.getInstance();
    private Map<Integer,ActivityDesc> activityLinkRow=new HashMap<>();
    private String actListChoose="tout";
    private Transition fadeTransition;
    private Scene scene_cal;



    private View.OnClickListener activityClickListenner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Data();

        Spinner sp=findViewById(R.id._spinner_activity);
        ArrayAdapter<String> dataAdapter= new ArrayAdapter<>(this, R.layout.spinner_item, Arrays.asList(getResources().getStringArray(R.array.activities)));
        sp.setAdapter(dataAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                erraseActivityTableLayout();
                actListChoose=((Spinner)findViewById(R.id._spinner_activity)).getItemAtPosition(i).toString();
                refreshActivityTableLayout(calendar,actListChoose);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        this.activityClickListenner=new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                System.out.println(activityLinkRow.get(view.getId()).getName());
                Intent intent=new Intent(MainActivity.this,ActivityView.class);
                intent.putExtra("activityId",activityLinkRow.get(view.getId()).getId());
                startActivity(intent);
            }
        };

        fadeTransition=new Fade();
        fadeTransition.setDuration(250);
        scene_cal =new Scene((ViewGroup)findViewById(R.id._scroll_cal));

        refreshActivityTableLayout(calendar,actListChoose);
        setUserName(Data.currentUser,((TextView)findViewById(R.id.currentusername)));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClickListenner(View view){
        switch(view.getId()){
            case R.id._View_Calendar:
                DatePickerDialog dp=new DatePickerDialog(view.getContext());
                dp.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dp.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int dayOfMonth) {
                        calendar.set(y,m,dayOfMonth);
                        erraseActivityTableLayout();
                        refreshActivityTableLayout(calendar,actListChoose);
                    }
                });
                dp.show();
                break;

            case R.id._view_timePicker:
                TimePickerDialog timePicker=new TimePickerDialog(MainActivity.this, new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int min) {
                        erraseActivityTableLayout();
                        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),h,min);
                        refreshActivityTableLayout(calendar,actListChoose);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                timePicker.show();
                break;

        }
    }



    public void erraseActivityTableLayout(){
        ViewGroup v=findViewById(R.id.ContentShow);
        v.removeAllViews();
    }
    public void refreshActivityTableLayout(Calendar selectCal,String actName){

        TableLayout mainActView=findViewById(R.id.ContentShow);//Recupere container principale de la liste d'activité

        TableLayout.LayoutParams lp =
                new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(10,10,10,10);

        TableRow row;
        ImageView image;
        TextView title;                 //initialise a null des widgets pour afficher les infos d'une activité
        TextView dateHour;
        TextView place;
        LinearLayout cont;
        Calendar actCal;

        //for(int i=0;i<10;i++) {     //affiche 10 fois les meme act pour genere plus d'activite facilement
            for (ActivityDesc act : Data.activity) {
                System.out.println(act.getDate().after(selectCal));
                if(act.getDate().after(selectCal) && (actName.equals("Tout") || actName.equals(act.getName()))) {
                    row = new TableRow(mainActView.getContext());
                    //row.setBackgroundColor(R.drawable.gradient);
                    row.setId(View.generateViewId());
                    row.setOnClickListener(this.activityClickListenner);
                    row.setBackgroundResource(R.drawable.gradient);
                    row.setLayoutParams(lp);
                    activityLinkRow.put(row.getId(), act);
                    cont = new LinearLayout(row.getContext());
                    image = new ImageView(row.getContext());
                    dateHour = new TextView(cont.getContext());     //creation des widgets
                    title = new TextView(cont.getContext());
                    place = new TextView(cont.getContext());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        place.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                    }
                    String hour;
                    String minute;

                    actCal = act.getDate();
                    hour = "" + actCal.get(Calendar.HOUR_OF_DAY);
                    minute = "" + actCal.get(Calendar.MINUTE);
                    if (actCal.get(Calendar.HOUR_OF_DAY) < 10) {
                        hour = "0" + actCal.get(Calendar.HOUR_OF_DAY);
                    }
                    if (actCal.get(Calendar.MINUTE) < 10) {
                        minute = "0" + actCal.get(Calendar.MINUTE);
                    }
                    row.setPadding(8, 16, 8, 8);
                    image.setPadding(8, 0, 8, 0);
                    image.setImageResource(ActivityDescImp.getIconFor(act.getName()));              //ajustement des parametre des different widgets(met l'image,le text etc etc)
                    cont.setOrientation(LinearLayout.VERTICAL);
                    title.setText(act.getName());
                    dateHour.setText("Le " + actCal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + " " + actCal.get(Calendar.DAY_OF_MONTH) + " " + actCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + actCal.get(Calendar.YEAR) + " à " + hour + ":" + minute);
                    place.setText("Emplacement: " + act.getPlace());
                    title.setPadding(8, 8, 8, 8);
                    dateHour.setPadding(8, 8, 8, 8);
                    place.setPadding(8, 8, 8, 8);

                    row.addView(image);
                    cont.addView(title);
                    cont.addView(dateHour);     //ajout des widgets dans les container puis les container dans le container principale definie &u dessus du for
                    cont.addView(place);
                    row.addView(cont);
                    mainActView.addView(row);
                }
            //}
        }
    }
    @Override
    public void onBackPressed(){
        System.out.println("Back Pressed");
        AuthActivity.deco(getApplicationContext());

    }
}
