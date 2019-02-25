package com.example.a21601861.cosport.Activity;

import com.example.a21601861.cosport.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ActivityDescImp implements ActivityDesc {

    private static int ID=0;
    private static Map<Integer,ActivityDesc> ACTIVITYLIST=new HashMap<>();
    private final int idIcon;
    private final int id;
    private final String name;
    private final String place;
    private final Calendar date;
    private final Integer idCreator;
    private List<Integer> idUserParticipate=new ArrayList<>();

    public ActivityDescImp(int idIcon, String name, String place, Calendar date,int idCreator) {
        this.idIcon=idIcon;
        this.id=ActivityDescImp.ID;
        ActivityDescImp.ID++;
        this.name=name;
        this.place=place;
        this.date=date;
        this.idCreator=idCreator;
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
    @Override
    public void addUser(int id){
        this.idUserParticipate.add(id);
    }
    @Override
    public void removeUser(int id){
        this.idUserParticipate.remove((Object)id);
    }
    @Override
    public List<Integer> getUserList(){
        return this.idUserParticipate;
    }

    @Override
    public boolean haveUser(int id) {
        return this.idUserParticipate.contains(id);
    }

    @Override
    public int getCreatorId(){
        return this.idCreator;
    }
    public static ActivityDesc getActivityById(int id){
        return ActivityDescImp.ACTIVITYLIST.get(id);
    }

    public static int getIcon(String act) {
        switch (act){
            case "Vélo":
                return R.mipmap.velo;

            case "Natation":
                return R.mipmap.natation;

            case "Escalade":
                return R.mipmap.escalade;

            case "Marche":
                return R.mipmap.marche;

            default:
                return -1;

        }
    }

    public static ArrayList<ActivityDesc> sort(ArrayList<ActivityDesc> activityUnsorted) {
        PriorityQueue<ActivityDesc> pq=new PriorityQueue<>(activityUnsorted.size(),new ActivityComparator());
        for(ActivityDesc act : activityUnsorted){
            pq.add(act);
        }
        ArrayList<ActivityDesc> listact=new ArrayList<>();
        for(int i=0;i<activityUnsorted.size();i++){
            listact.add(pq.poll());
        }
        return listact;
    }
}
