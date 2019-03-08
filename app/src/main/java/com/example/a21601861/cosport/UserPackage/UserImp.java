package com.example.a21601861.cosport.UserPackage;

public class UserImp implements User {
    private int idProfilPicture;
    private String log;
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


    public UserImp(int idProfilPict , String log,String prenom,String nom,int age,String mail,String mdp,String genre,String desc,int id){
        this.idProfilPicture=idProfilPict;
        this.log=log;
        this.id=id;
        this.prenom=prenom;
        this.nom=nom;
        this.age=age;
        this.mail=mail;
        this.mdp=mdp;
        this.genreCode=genre;
        this.decr=desc;
    }
    public UserImp(String url , String name,String prenom,String nom,int age,String mail,String mdp,String genre,String desc,int id ){
        this.idProfilPicture=-1;
        this.urlProfilPicture=url;
        this.hasURLProfilPicture =true;
        this.log =name;
        this.id=id;
        this.prenom=prenom;
        this.nom=nom;
        this.age=age;
        this.mail=mail;
        this.mdp=mdp;
        this.genreCode=genre;
        this.decr=desc;
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
    public String getLog() {
        return this.log;
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
        return this.decr;
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
