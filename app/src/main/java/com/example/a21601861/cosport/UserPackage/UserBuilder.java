package com.example.a21601861.cosport.UserPackage;

import com.example.a21601861.cosport.R;

public class UserBuilder {
    private String prenom="";
    private String nom="";
    private String mail="";
    private String log="";
    private String mdp1="";
    private String mdp2="";
    private String genre="";
    private String desc="";
    private int age=-1;
    private String error="";

    public UserBuilder setNom(String nom){
        this.nom=nom;
        return this;
    }
    public UserBuilder setPrenom(String prenom){
        this.prenom=prenom;
        return this;
    }
    public UserBuilder setMail(String mail){
        this.mail=mail;
        return this;
    }
    public UserBuilder setLog(String log){
        this.log=log;
        return this;
    }
    public UserBuilder setMdp(String mdp1,String mdp2){
        this.mdp1=mdp1;
        this.mdp2=mdp2;
        return this;
    }
    public UserBuilder setGenre(String genreCode){
        this.genre=genreCode;
        return this;
    }
    public UserBuilder setDesc(String descr){
        this.desc=descr;
        return this;
    }
    public UserBuilder setAge(String age){
        try {
            this.age = Integer.parseInt(age);
        }
        catch (NumberFormatException e){
            this.age=-1;
        }
        return this;
    }
    public boolean isValid(){
        boolean code=true;
        this.error="";
        if(prenom.equals("")){
            error+="le prénom ne devrait pas être vide ";
            code=false;
        }
        if(nom.equals("")){
            error+="le nom ne devrait pas être vide ";
            code=false;
        }
        if(mail.equals("")){
            error+="le mail ne devrait pas être vide ";
            code=false;
        }

        if(log.equals("")){
            error+="le pseudo ne devrait pas être vide ";
            code=false;
        }
        if(mdp1.equals("") || mdp2.equals("")){
            error+="le mot de passe ne devrait pas être vide ";
            code=false;
        }
        else if(!mdp1.equals(mdp2)){
            error+="les mots de passe devraient correspondre ";
            code=false;
        }
        if(genre.equals("")){
            error+="le genre ne devrait pas être vide ";
            code=false;
        }
        if(age<1){
            error+="l'âge devrait être positif ";
            code=false;
        }
        return code;
    }

    public String getError() {
        return error;
    }

    public User build(){
        return new UserImp(R.mipmap.tete,log,prenom,nom,age,mail,mdp1,genre,desc);
    }
}
