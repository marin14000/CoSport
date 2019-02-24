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
import java.util.Random;

public class DataTest {
    private static Calendar date1 =Calendar.getInstance();
    private static Calendar date2 =Calendar.getInstance();
    private static Calendar date3 =Calendar.getInstance();
    private static Calendar date4 =Calendar.getInstance();
    private static Calendar date5 =Calendar.getInstance();
    private static Calendar date6 =Calendar.getInstance();
    private static Calendar date7 =Calendar.getInstance();
    private static Calendar date8 =Calendar.getInstance();
    private static Calendar date9 =Calendar.getInstance();
    private static Calendar date10 =Calendar.getInstance();
    private static Calendar date11=Calendar.getInstance();
    private static Calendar date12=Calendar.getInstance();
    private static Calendar date13=Calendar.getInstance();
    private static Calendar date14 =Calendar.getInstance();
    private static Calendar date15 =Calendar.getInstance();
    static{
        date1.set(2019,0,18,18,0);
        date2.set(2019,1,9,18,0);
        date3.set(2019,2,9,18,0);
        date4.set(2019,3,9,18,0);

        date5.set(2019,2,10,12,30);
        date6.set(2019,2,2,15,0);
        date7.set(2019,2,1,12,0);
        date8.set(2019,2,5,17,0);
        date9.set(2019,2,6,20,0);
        date10.set(2019,2,14,22,0);
        date11.set(2019,2,18,16,0);
        date12.set(2019,2,6,11,0);
        date13.set(2019,2,25,10,0);
        date14.set(2019,2,1,7,0);
        date15.set(2019,2,3,6,0);
    }


    private static List<User> userList=new ArrayList<>(Arrays.asList((User)
            //new UserImp("http://ubuntu-stream.ddns.net:1278/img/homme.png","toto","catherine","de Saint Jores",20,"dsjcatherine@gmail.com","456789","f","blablabla"),
                    new UserImp("http://192.168.1.15/images/homme.png","toto","catherine","de Saint Jores",20,"dsjcatherine@gmail.com","456789","f","blablabla"),
            //new UserImp("http://ubuntu-stream.ddns.net:1278/img/femme.png","tota","julien","dupuis",22,"dupuis@gmail.com","1599","h","blablabla"),
            new UserImp("http://192.168.1.15/images/femme.png","tota","julien","dupuis",22,"dupuis@gmail.com","1599","h","blablabla"),
            new UserImp(R.mipmap.tete,"toti","marine","bleu",25,"marine@gmail.com","7532","f","blablabla"),
            new UserImp(R.mipmap.tete,"tooa","caroline","delahaye",35,"delahaye@unicaen.fr","4521","f","blablavbla"),
            new UserImp(R.mipmap.tete,"tote","bruno","lorange",45,"lorange@hotmail.fr","78521","h","blablabla"),
            new UserImp(R.mipmap.tete,"tito","louise","attaque",19,"attaque@gmail.com","8426","f","blablabla"),
            new UserImp(R.mipmap.tete,"tato","charlotte","la Fourmi",24,"charlotelafourmi@gmail.com","1535","f","blablabla"),

            new UserImp(R.mipmap.tete,"ben","benjamin","de l'amour",23,"delamour@gmail.com","mimomu","h","blablabla"),
            new UserImp(R.mipmap.tete,"catherine","catherine","delarue",30,"delarue@hotmail.fr","qgqkejfrgjqkeng","f","blablabla"),
            new UserImp(R.mipmap.tete,"marin","marin","lechamps",29,"lechamps@gmail.com","zzf64Z","h","blablabla"),
            new UserImp(R.mipmap.tete,"julien","julien","pataproute",35,"pataproue@gmail.com","rglsk","h","blablabla"),
            new UserImp(R.mipmap.tete,"adrien","adrien","delaplante",19,"delaplante@gmail.com","78+45","h","blablabla")
    ));

    public static ArrayList<ActivityDesc> activityUnsorted=new ArrayList<>(Arrays.asList((ActivityDesc)
            new ActivityDescImp(R.mipmap.escalade, "Escalade", "suaps", date1,userList.get(0).getId()),
            new ActivityDescImp(R.mipmap.marche,"Marche","chemin vert", date2,userList.get(1).getId()),
            new ActivityDescImp(R.mipmap.natation,"Natation","suaps", date3,userList.get(2).getId()),
            new ActivityDescImp(R.mipmap.velo,"Vélo","campus 2", date4,userList.get(3).getId()),
            new ActivityDescImp(R.mipmap.escalade,"Escalade","Clécy",date5,userList.get(4).getId()),
            new ActivityDescImp(R.mipmap.marche,"Marche","Caen",date6,userList.get(5).getId()),
            new ActivityDescImp(R.mipmap.natation,"Natation","Caen stade nautique eugene Maes",date7,userList.get(6).getId()),

            new ActivityDescImp(R.mipmap.velo,"Vélo","Caen Centre",date8,userList.get(0).getId()),
            new ActivityDescImp(R.mipmap.escalade,"Escalade","chemin Vert",date9,userList.get(1).getId()),
            new ActivityDescImp(R.mipmap.escalade,"Escalade","La bréche du diable",date10,userList.get(2).getId()),
            new ActivityDescImp(R.mipmap.marche,"Marche","Chemin Vert",date11,userList.get(3).getId()),
            new ActivityDescImp(R.mipmap.natation,"Natation","Piscine municipal de Caen",date12,userList.get(4).getId()),
            new ActivityDescImp(R.mipmap.escalade,"Escalade","Clécy",date13,userList.get(5).getId()),
            new ActivityDescImp(R.mipmap.marche,"Marche","Clécy",date14,userList.get(0).getId()),
            new ActivityDescImp(R.mipmap.velo,"Vélo","Clécy",date15,userList.get(1).getId())
    ));

    public static ArrayList<ActivityDesc> activity =ActivityDescImp.sort(activityUnsorted);
    static{
        Random r=new Random();
        for(ActivityDesc act: activity){
            for(User user:userList){
                if(act.getCreatorId()!=user.getId()){
                    ///if(r.nextInt(2)==1) {
                        act.addUser(user.getId());
                  // }
                }
            }
        }
    }

    public static User currentUser=new UserImp("http://192.168.1.15/images/homme.png","admin","marin","Dorange",20,"marin.do@hotmail.fr","0123456789","h","" +
            "Passioné d'escalade je pratique ce sport depuis 4 à 5 ans. Depuis peu, je me suis mis à découvrir et à aimer l'escalade en extérieur malheureusement " +
            "il est difficile de trouver des partenaires voulant grimper avec moi. C'est donc pour cela que j'ai créé Co-Sport une application de rencontre pour les amoureux du sport" +
            "En effet, cette application permet de rencontrer d'autres gens pour faire du sport.");
}
