package com.example.a21601861.cosport;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static Calendar calendar=Calendar.getInstance();
    private Map<Integer,ActivityDesc> activityLinkRow=new HashMap<>();
    private String actListChoose="tout";

    private Transition slideTransition;
    private Scene scene_menu;
    private Transition fadeTransition;
    private Scene scene_cal;

    private static Calendar test=Calendar.getInstance();
    static{test.set(2019,0,18,18,0);}
    private static Calendar test2=Calendar.getInstance();
    static{test2.set(2019,1,9,18,0);}
    private static Calendar test3=Calendar.getInstance();
    static{test3.set(2019,2,9,18,0);}
    private static Calendar test4=Calendar.getInstance();
    static{test4.set(2019,3,9,18,0);}

    private ArrayList<ActivityDesc> activity=new ArrayList<>(Arrays.asList(new ActivityDescImp(R.mipmap.escalade, "Escalade", "suaps",test ), new ActivityDescImp(R.mipmap.marche,"Marche","chemin vert",test2), (ActivityDesc) new ActivityDescImp(R.mipmap.natation,"Natation","suaps",test3),new ActivityDescImp(R.mipmap.velo,"Vélo","campus 2",test4)));
    private View.OnClickListener activityClickListenner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView cv= findViewById(R.id.CalPicker);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                erraseActivityTableLayout();
                calendar.set(y,m,d);
                refreshActivityTableLayout(calendar,actListChoose);
            }
        });
        ((Spinner)findViewById(R.id._spinner_activity)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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





        slideTransition=new Slide(Gravity.RIGHT);
        slideTransition.setDuration(1000);
        fadeTransition=new Fade();
        fadeTransition.setDuration(250);
        scene_menu =new Scene((ViewGroup)findViewById(R.id.menu));
        scene_cal =new Scene((ViewGroup)findViewById(R.id._scroll_cal));

        refreshActivityTableLayout(calendar,actListChoose);

    }

    public void onClickListenner(View view){
        switch(view.getId()){
            case R.id._icon_menu:
                TransitionManager.go(scene_menu, slideTransition);
                findViewById(R.id.menu).setVisibility(View.VISIBLE);
                break;

            case R.id._View_Calendar:
                if(findViewById(R.id._scroll_cal).getVisibility()==View.GONE) {
                    TransitionManager.go(scene_cal, fadeTransition);
                    findViewById(R.id._scroll_cal).setVisibility(View.VISIBLE);
                }
                else {
                    TransitionManager.go(scene_cal, fadeTransition);
                    findViewById(R.id._scroll_cal).setVisibility(View.GONE);
                }
                break;

            case R.id._view_timePicker:
                System.out.println("hello");
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

            case R.id.return_menu:
                TransitionManager.go(scene_menu, slideTransition);
                findViewById(R.id.menu).setVisibility(View.GONE);
                break;

        }
    }



    public void erraseActivityTableLayout(){
        ViewGroup v=findViewById(R.id.ContentShow);
        v.removeAllViews();
    }
    public void refreshActivityTableLayout(Calendar selectCal,String actName){

        TableLayout mainActView=findViewById(R.id.ContentShow);//Recupere container principale de la liste d'activité
        TableRow row;
        ImageView image;
        TextView title;                 //initialise a null des widgets pour afficher les infos d'une activité
        TextView dateHour;
        TextView place;
        LinearLayout cont;
        Calendar actCal;
        System.out.println(actName);
        for(int i=0;i<10;i++) {     //affiche 10 fois les meme act pour genere plus d'activite facilement
            for (ActivityDesc act : activity) {
                if(act.getDate().after(selectCal) && (actName.equals("tout") || actName.equals(act.getName()))) {
                    row = new TableRow(mainActView.getContext());
                    row.setId(View.generateViewId());
                    row.setOnClickListener(this.activityClickListenner);
                    activityLinkRow.put(row.getId(), act);
                    cont = new LinearLayout(row.getContext());
                    image = new ImageView(row.getContext());
                    dateHour = new TextView(cont.getContext());     //creation des widgets
                    title = new TextView(cont.getContext());
                    place = new TextView(cont.getContext());
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
                    image.setImageResource(act.getIconId());              //ajustement des parametre des different widgets(met l'image,le text etc etc)
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
            }
        }
    }


}