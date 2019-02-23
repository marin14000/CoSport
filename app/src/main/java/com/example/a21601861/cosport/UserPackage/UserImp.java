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
    private int age;
    private String genreCode;
    private String decr;
    private String prenom;
    private String nom;
    private String mail;
    private String mdp;

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
    public UserImp(int idProfilPict , String name,String prenom,String nom,int age,String mail,String mdp,String genre,String desc ){
        this.idProfilPicture=idProfilPict;
        this.name=name;
        this.id=IDGEN;
        IDGEN++;
        this.prenom=prenom;
        this.nom=nom;
        this.age=age;
        this.mail=mail;
        this.mdp=mdp;
        this.genreCode=genre;
        this.decr=desc;
        USERMAP.put(this.id,this);
    }
    public UserImp(String url , String name,String prenom,String nom,int age,String mail,String mdp,String genre,String desc ){
        this.idProfilPicture=-1;
        this.urlProfilPicture=url;
        this.hasURLProfilPicture =true;
        this.name=name;
        this.id=IDGEN;
        IDGEN++;
        this.prenom=prenom;
        this.nom=nom;
        this.age=age;
        this.mail=mail;
        this.mdp=mdp;
        this.genreCode=genre;
        this.decr=desc;
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

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getGenre() {
        return genreCode;
    }

    @Override
    public String getDescription() {
        return decr;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getMDP() {
        return mdp;
    }

    public static User findUserById(int id){
        return UserImp.USERMAP.get(id);
    }

    public static String decode(String genre) {
        switch (genre){
            case "h":
                return "Homme";
            case "f":
                return "Femme";
            case "nh":
                return "Aucun";
        }
        return "";
    }
}
