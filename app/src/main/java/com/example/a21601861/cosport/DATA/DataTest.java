package com.example.a21601861.cosport.DATA;

import com.example.a21601861.cosport.Activity.ActivityDesc;
import com.example.a21601861.cosport.Activity.ActivityDescImp;
import com.example.a21601861.cosport.R;
import com.example.a21601861.cosport.UserPackage.User;
import com.example.a21601861.cosport.UserPackage.UserImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DataTest {
    private static Calendar test=Calendar.getInstance();
    static{test.set(2019,0,18,18,0);}
    private static Calendar test2=Calendar.getInstance();
    static{test2.set(2019,1,9,18,0);}
    private static Calendar test3=Calendar.getInstance();
    static{test3.set(2019,2,9,18,0);}
    private static Calendar test4=Calendar.getInstance();
    static{test4.set(2019,3,9,18,0);}

    private static List<User> userList=new ArrayList<>(Arrays.asList(new UserImp(R.mipmap.tete,"toto"),new UserImp(R.mipmap.tete,"tota"),new UserImp(R.mipmap.tete,"toti"),new UserImp(R.mipmap.tete,"tooa"),new UserImp(R.mipmap.tete,"tote"),new UserImp(R.mipmap.tete,"tito"),(User)new UserImp(R.mipmap.tete,"tato")));

    public static ArrayList<ActivityDesc> activity=new ArrayList<>(Arrays.asList(new ActivityDescImp(R.mipmap.escalade, "Escalade", "suaps",test,userList.get(0).getId()), new ActivityDescImp(R.mipmap.marche,"Marche","chemin vert",test2,userList.get(1).getId()), (ActivityDesc) new ActivityDescImp(R.mipmap.natation,"Natation","suaps",test3,userList.get(2).getId()),new ActivityDescImp(R.mipmap.velo,"Vélo","campus 2",test4,userList.get(3).getId())));

    static{
        for(ActivityDesc act: activity){
            for(User user:userList){
                if(act.getCreatorId()!=user.getId()){
                    act.addUser(user.getId());
                }
            }
        }
    }

    public static User currentUser=new UserImp("http://192.168.1.1/images/tete.png","currentUser");
}
