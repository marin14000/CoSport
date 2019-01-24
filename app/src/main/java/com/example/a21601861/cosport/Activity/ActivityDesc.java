package com.example.a21601861.cosport.Activity;

import java.util.Calendar;
import java.util.List;

public interface ActivityDesc {
    int getIconId();
    int getId();
    String getName();
    String getPlace();
    Calendar getDate();
    void addUser(int id);
    void removeUser(int id);
    List<Integer> getUserList();
    boolean haveUser(int id);
    int getCreatorId();
}
