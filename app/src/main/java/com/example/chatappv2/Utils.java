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
    private ArrayList<Professor> profsPG1 = new ArrayList<>();
    private ArrayList<Professor> profsITS = new ArrayList<>();
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
        profsAuD.add(new Professor("Jung",4.0, "AUD Professor in Informatik Fakultat", "https://static.wikia.nocookie.net/dccu/images/b/b1/Generic_Placeholder.png/revision/latest?cb=20140107063435" ));
        profsAuD.add(new Professor("Alternbend",4.0, "AUD Professor in Informatik Fakultat", "https://static.wikia.nocookie.net/dccu/images/b/b1/Generic_Placeholder.png/revision/latest?cb=20140107063435" ));
        profsPG1.add(new Professor("Skroch",0, "PG1 Professor in Informatik Fakultat", "https://static.wikia.nocookie.net/dccu/images/b/b1/Generic_Placeholder.png/revision/latest?cb=20140107063435"));
        profsITS.add(new Professor("Rathgeb", 0, "ITS Professor in Informatik Fakultat", "https://static.wikia.nocookie.net/dccu/images/b/b1/Generic_Placeholder.png/revision/latest?cb=20140107063435"));

        profsMathe.add(new Professor("Pepe the Frog", 5.0, "Mathe 1 Professor in Informatik Fakultat", "https://ichef.bbci.co.uk/news/976/cpsprodpb/16620/production/_91408619_55df76d5-2245-41c1-8031-07a4da3f313f.jpg"));

    }

    public static Utils getInstance() {
        return instance;
    }

    public static void setInstance(Utils instance) {
        Utils.instance = instance;
    }

    public ArrayList<Professor> getProfsMathe() {
        return profsMathe;
    }

    public void setProfsMathe(ArrayList<Professor> profsMathe) {
        this.profsMathe = profsMathe;
    }

    public ArrayList<Professor> getProfsTGI() {
        return profsTGI;
    }

    public void setProfsTGI(ArrayList<Professor> profsTGI) {
        this.profsTGI = profsTGI;
    }

    public ArrayList<Professor> getProfsAuD() {
        return profsAuD;
    }

    public void setProfsAuD(ArrayList<Professor> profsAuD) {
        this.profsAuD = profsAuD;
    }

    public ArrayList<Professor> getProfsPG1() {
        return profsPG1;
    }

    public void setProfsPG1(ArrayList<Professor> profsPG1) {
        this.profsPG1 = profsPG1;
    }

    public ArrayList<Professor> getProfsITS() {
        return profsITS;
    }

    public void setProfsITS(ArrayList<Professor> profsITS) {
        this.profsITS = profsITS;
    }

    public static Utils getInstance(Context context) { //synchronized make it thread safe
        if(null != instance)
            return instance;
        else{
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Professor> getAllProfs(String moduleName) {
        switch (moduleName) {
            case "AUD":
                return profsAuD;
            case "ITS":
                return profsITS;
            case "MATHE 1":
                return profsMathe;
            case "PG 1":
                return profsPG1;
            case "TGI":
                return profsTGI;
            default:
                return null;
        }
    }
}
