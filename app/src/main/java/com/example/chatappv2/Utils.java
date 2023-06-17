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
    private ArrayList<Professor> profsMathe = new ArrayList<>();
    private ArrayList<Professor> profsTGI = new ArrayList<>();
    private ArrayList<Professor> profsAuD = new ArrayList<>();
    private ArrayList<Professor> pg1 = new ArrayList<>();
    private ArrayList<Professor> its = new ArrayList<>();
    public Utils(){
        initData();
    }
    public Utils(Context context) {
        initData();
    }

    private void initData(){
        profsMathe.add(new Professor("Hechler", 5.0, "Mathe 1 Professor in Informatik Fakultat", "https://static.vecteezy.com/system/resources/thumbnails/009/383/709/small/woman-face-expression-clipart-design-illustration-free-png.png"));

        profsMathe.add(new Professor("Romana Piat", 5.0, "Mathe 1 Professor in Informatik Fakultat", "https://www.itm.kit.edu/img/PiatNew2(1).jpg"));

        profsTGI.add(new Professor("Maier", 4.0, "TGI Professor in Informatik Fakultat", "https://staticg.sportskeeda.com/editor/2021/04/f7504-16196838481260-800.jpg"));
        profsAuD.add(new Professor("Jung",4.0, "TGI Professor in Informatik Fakultat", "https://staticg.sportskeeda.com/editor/2021/04/f7504-16196838481260-800.jpg" ));
    }

    public static Utils getInstance(Context context) { //synchronized make it thread safe
        if(null != instance)
            return instance;
        else{
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Professor> getAllProfs(int moduleNumber) {
        switch (moduleNumber) {
            case 0:
                return profsAuD;
            case 1:
                return its;
            case 2:
                return profsMathe;
            case 3:
                return pg1;
            case 4:
                return profsTGI;
            default:
                return null;
        }
    }
}
