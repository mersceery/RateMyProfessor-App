package com.example.chatappv2;


import android.content.Context;

import com.example.chatappv2.allProfs.Professor;
import com.example.chatappv2.listEmails.User;

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
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public Utils(Context context) {
        initData();
    }

    private void initData(){
        profs.add(new Professor("Hechler", 5.0, "Mathe 1 Professor in Informatik Fakultat", "https://static.vecteezy.com/system/resources/thumbnails/009/383/709/small/woman-face-expression-clipart-design-illustration-free-png.png"));

        profs.add(new Professor("Romana Piat", 5.0, "Mathe 1 Professor in Informatik Fakultat", "https://www.itm.kit.edu/img/PiatNew2(1).jpg"));

        //profs.add(new Professor("KEKW", 5.0, "Z Professor in Informatik Fakultat", "https://staticg.sportskeeda.com/editor/2021/04/f7504-16196838481260-800.jpg"));

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
