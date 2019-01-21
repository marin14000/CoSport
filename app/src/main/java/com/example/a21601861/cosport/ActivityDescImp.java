package com.example.a21601861.cosport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityDescImp implements ActivityDesc {

    private static int ID=0;
    private static Map<Integer,ActivityDesc> ACTIVITYLIST=new HashMap<>();
    private final int idIcon;
    private final int id;
    private final String name;
    private final String place;
    private final Calendar date;

    public ActivityDescImp(int idIcon, String name, String place, Calendar date) {
        this.idIcon=idIcon;
        this.id=ActivityDescImp.ID;
        ActivityDescImp.ID++;
        this.name=name;
        this.place=place;
        this.date=date;
        ActivityDescImp.ACTIVITYLIST.put(this.id,this);

    }
    @Override
    public int getIconId() {
        return this.idIcon;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPlace() {
        return this.place;
    }

    @Override
    public Calendar getDate() {
        return this.date;
    }
    public static ActivityDesc getActivityById(int id){
        return ActivityDescImp.ACTIVITYLIST.get(id);
    }
}
