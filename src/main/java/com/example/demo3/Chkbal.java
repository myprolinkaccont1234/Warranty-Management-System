package com.example.demo3;


import javafx.stage.Stage;

public class Chkbal {
    private  int  ca,cb,ce,cf;
    private String cc,cd,ch;

    public Chkbal(int ca,String ch,int cb, String cc, String cd,int ce,int cf) {
        this.ca = ca;
        this.cb = cb;
        this.cc = cc;
        this.cd = cd;
        this.ce = ce;
        this.cf = cf;
        this.ch = ch;
    }

    public int getCa() {
        return ca;
    }
    public int getCb() {
        return cb;
    }

    public String getCc() {
        return cc;
    }

    public String getCd() {
        return cd;
    }

    public int getCe() {
        return ce;
    }

    public int getCf() {
        return cf;
    }

    public String getCh() {
        return ch;
    }


    public void setCa(int ca) {
        this.ca = ca;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }
    public void setCe(int ce) {
        this.ce = ce;
    }
    public void setCf(int cf) {
        this.cf = cf;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

@Override
    public String toString(){

        return "Chkbal{"+", ca="+ca+", cb="+cb+", cc="+cc+", cd="+cd+", ce="+ce+'}';
}

void loader() throws Exception {
    new Modify_data(ca);
        Modify_data gh=new Modify_data();
        gh.start(new Stage());
}


}