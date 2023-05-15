package com.example.chatappv2;


import android.content.Context;

import com.example.chatappv2.allProfs.Professor;

import java.util.ArrayList;

public class Utils {

    public String emailTemp;

    public String getEmailTemp() {
        return emailTemp;
    }

    public void setEmailTemp(String emailTemp) {
        this.emailTemp = emailTemp;
    }


    private static Utils instance;
    private ArrayList<Professor> profs = new ArrayList<>();


    public Utils(Context context) {
        initData();
    }

    private void initData(){
        profs.add(new Professor("Prof 1", 4.5, "X Professor in Informatik Fakultat", "https://i.pinimg.com/originals/30/df/1c/30df1cb8981338d42ed2722ab74cb51e.jpg"));

        profs.add(new Professor("Prof 2", 4.0, "Y Professor in Informatik Fakultat", "https://as1.ftcdn.net/v2/jpg/02/51/13/68/1000_F_251136811_2sMnZjYICQ5eLPJqc8axlF1xHqoP3xov.jpg"));

        profs.add(new Professor("KEKW", 5.0, "Z Professor in Informatik Fakultat", "https://staticg.sportskeeda.com/editor/2021/04/f7504-16196838481260-800.jpg"));

    }

    public static Utils getInstance(Context context) { //synchronized make it thread safe
        if(null != instance)
            return instance;
        else{
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Professor> getAllProfs(){
        return profs;
    }
}
