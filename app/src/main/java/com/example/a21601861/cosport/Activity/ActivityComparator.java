package com.example.a21601861.cosport.Activity;

import java.util.Comparator;

public class ActivityComparator implements Comparator<ActivityDesc> {
    @Override
    public int compare(ActivityDesc act1, ActivityDesc act2) {
        if(act1.getDate().before(act2.getDate())){
            return -1;
        }
        else if(act1.getDate().after(act2.getDate())){
            return 1;
        }
        return 0;
    }
}
