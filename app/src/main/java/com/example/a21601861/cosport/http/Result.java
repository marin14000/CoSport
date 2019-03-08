package com.example.a21601861.cosport.http;

import com.example.a21601861.cosport.Activity.ActivityDesc;
import com.example.a21601861.cosport.Activity.ActivityDescImp;
import com.example.a21601861.cosport.UserPackage.User;
import com.example.a21601861.cosport.UserPackage.UserBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Result {

    private String data;
    private List<User> user=new ArrayList<>();
    private List<ActivityDesc> act=new ArrayList<>();
    private Integer id;
    public Result(String data){
        this.data=data;
        this.decode();
    }

    public void decode(){
        int indexList=this.data.indexOf("<list>");
        if(indexList!=-1){
            decodeList(this.data.substring(indexList+6,this.data.indexOf("</list>")));
        }
        else{
            if(this.data.indexOf("<activite>")!=-1){
                act.add(decodeAct(data.substring(data.indexOf("<activite>")+10,data.indexOf("</activite>"))));
            }
            else if(this.data.indexOf("<user>")!=-1){
                user.add(decodeUser(data.substring(data.indexOf("<user>")+6,data.indexOf("</user>"))));
            }
            else if(this.data.indexOf("<id>")!=-1){
                this.id=Integer.parseInt((data.substring(data.indexOf("<id>")+4,data.indexOf("</id>"))).trim());
            }
        }
    }
    public void decodeList(String array){
        array=array.trim();
        String work;
        System.out.println(array);
        System.out.println(array.indexOf("<user>"));
        while(array.indexOf("<activite>")!=-1){
            work=array.substring(array.indexOf("<activite>")+10,array.indexOf("</activite>"));
            System.out.println("work on: "+work);
            array=array.replace("<activite>"+work+"</activite>","");
            act.add(decodeAct(work));
        }
        while(array.indexOf("<user>")!=-1){
                work=array.substring(array.indexOf("<user>")+6,array.indexOf("</user>"));
                System.out.println(work);
                array=array.replace("<user>"+work+"</user>","");
                user.add(decodeUser(work));
            }
    }
    public User decodeUser(String user){

        String[] dataSplit1=user.split("\\*");
        UserBuilder ub=new UserBuilder();

        for(String str:dataSplit1) {
            String[]dataSplit=str.split("=");
            String strK=""+dataSplit[0].trim();
            String strV=""+dataSplit[1].trim();


                if ("nom".equals(strK)) {
                    ub.setNom(strV);

                } else if ("prenom".equals(strK)) {
                    ub.setPrenom(strV);

                } else if ("age".equals(strK)) {
                    ub.setAge(strV);

                } else if ("mail".equals(strK)) {
                    ub.setMail(strV);

                } else if ("mdp".equals(strK)) {
                    ub.setMdp(strV, strV);

                } else if ("sexe".equals(strK)) {
                    ub.setGenre(strV);

                } else if ("pseudos".equals(strK)) {
                    ub.setLog(strV);

                } else if ("id".equals(strK)) {
                    ub.setId(Integer.parseInt(strV));
                }
                else if("description".equals(strK)){
                    ub.setDesc(strV);
                }

        }
        User u=ub.build();
        return u;
    }
    public ActivityDesc decodeAct(String act){

        String place="";
        String name="";
        String id="";
        int creator=-1;
        Calendar date=Calendar.getInstance();


        String[] dataSplit=act.split("\\*");
        for(int i=0;i<dataSplit.length;i++) {
            String strK=(dataSplit[i].split("=")[0]).trim();
            String strV=(dataSplit[i].split("=")[1]).trim();

            if ("place".equals(strK)) {
                place = strV;

            } else if ("date".equals(strK)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
                Date dateSDF;
                try {
                    dateSDF = sdf.parse(strV);
                    date.setTime(dateSDF);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            } else if ("name".equals(strK)) {
                name = strV;

            } else if ("id".equals(strK)) {
                id = strV;

            } else if ("creator".equals(strK)) {
                creator=Integer.parseInt(strV);
            }
        }
        ActivityDesc activity=new ActivityDescImp(ActivityDescImp.getIconFor(name),name,place,date,creator,id);
        return activity;
    }


    public List<ActivityDesc> getListOfAct(){
        return this.act;
    }
    public List<User> getListOfUser(){
        return this.user;
    }
    public User getUser(){
        return this.user.get(0);
    }
    public ActivityDesc getAct(){
        return this.act.get(0);
    }
    public int getIdOfUser(){
        return this.id;
    }

}
