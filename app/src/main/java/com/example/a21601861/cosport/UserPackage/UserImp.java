package com.example.a21601861.cosport.UserPackage;

import java.util.HashMap;
import java.util.Map;

public class UserImp implements User {
    private static Map<Integer,User> USERMAP =new HashMap() ;
    private int idProfilPicture;
    private String name;
    private static int IDGEN=0;
    private int id;
    private String urlProfilPicture;
    private boolean hasURLProfilPicture =false;
    public UserImp(int idProfilPict , String name){
        this.idProfilPicture=idProfilPict;
        this.name=name;
        this.id=IDGEN;
        IDGEN++;
        USERMAP.put(this.id,this);
    }
    public UserImp(String url , String name){
        this.idProfilPicture=-1;
        this.urlProfilPicture=url;
        this.hasURLProfilPicture =true;
        this.name=name;
        this.id=IDGEN;
        IDGEN++;
        USERMAP.put(this.id,this);
    }
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getProfilPicture() {
        return this.idProfilPicture;
    }

    @Override
    public String getURLOfProfilPicture() {
        return urlProfilPicture;
    }

    @Override
    public boolean hasURLProfilPicture() {
        return this.hasURLProfilPicture;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static User findUserById(int id){
        return UserImp.USERMAP.get(id);
    }

}
