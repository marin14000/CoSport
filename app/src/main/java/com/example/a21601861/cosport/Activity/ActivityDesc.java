package com.example.a21601861.cosport.Activity;

import com.example.a21601861.cosport.UserPackage.User;

import java.util.Calendar;
import java.util.List;

public interface ActivityDesc {
    int getIconId();
    int getId();
    String getName();
    String getPlace();
    Calendar getDate();
    void addUser(User user);
    void addUserList(List<User> listUser);
    void removeUser(int user);
    List<User> getUserList();
    boolean haveUser(int user);
    User getCreator();
}
