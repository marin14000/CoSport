package com.example.a21601861.cosport.Activity;

import com.example.a21601861.cosport.R;
import com.example.a21601861.cosport.UserPackage.User;
import com.example.a21601861.cosport.http.DataStorageAccess;

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
    private List<User> userParticipate =new ArrayList<>();
    private int creator;

    public ActivityDescImp(int idIcon, String name, String place, Calendar date,int creator) {
        this.idIcon=idIcon;
        this.id=ActivityDescImp.ID;
        ActivityDescImp.ID++;
        this.name=name;
        this.place=place;
        this.date=date;
        this.creator =creator;
        ActivityDescImp.ACTIVITYLIST.put(this.id,this);

    }

    public ActivityDescImp(int idIcon, String name, String place, Calendar date, int creator,String id) {
        this.idIcon=idIcon;
        this.id= Integer.parseInt(id);
        this.name=name;
        this.place=place;
        this.date=date;
        this.creator =creator;
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
    public void addUser(User user){
        this.userParticipate.add(user);
    }

    @Override
    public void addUserList(List<User> listUser) {
        this.userParticipate.addAll(listUser);
    }

    @Override
    public void removeUser(int user){
        for (User u:userParticipate) {
            if(u.getId()==user){
                userParticipate.remove(u);
                return;
            }
        }

    }
    @Override
    public List<User> getUserList(){
        return this.userParticipate;
    }

    @Override
    public boolean haveUser(int user) {
        for (User u:this.userParticipate
             ) {
            if(user==u.getId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCreator(){
        DataStorageAccess dsa=DataStorageAccess.getInstance();
        dsa.getUser(this.creator);
        return dsa.getResult().getUser();
    }
    public static ActivityDesc getActivityById(int id){
        return ActivityDescImp.ACTIVITYLIST.get(id);
    }

    public static ArrayList<ActivityDesc> sort(List<ActivityDesc> activityUnsorted) {
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

    public static int getIconFor(String act) {
        switch (act){
            case "Vélo":
                return R.mipmap.velo;
            case "Natation":
                return R.mipmap.natation;
            case "Escalade":
                return R.mipmap.escalade;
            case "Marche":
                return R.mipmap.marche;
        }
        return -1;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
