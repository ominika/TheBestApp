package com.example.dominika.appobchod;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Beata on 21.04.2016.
 */
public class PatientsListViewItem implements Serializable {
    private static final long serialVersionUID = -5435670920302756945L;

    public int id;
    public String title;
    //public String B1;
    //public String B2;

    public PatientsListViewItem(){

    }

    public PatientsListViewItem(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
