package com.example.demo3;



public class Detail {
int fc,fic;
String sc,tc,foc,sic,sec;

    public Detail(int fc, int fic, String sc, String tc, String foc, String sic, String sec) {
        this.fc = fc;
        this.fic = fic;
        this.sc = sc;
        this.tc = tc;
        this.foc = foc;
        this.sic = sic;
        this.sec = sec;
    }

    public int getFc() {
        return fc;
    }

    public int getFic() {
        return fic;
    }

    public String getSc() {
        return sc;
    }

    public String getTc() {
        return tc;
    }

    public String getFoc() {
        return foc;
    }

    public String getSic() {
        return sic;
    }

    public String getSec() {
        return sec;
    }

    public void setFc(int fc) {
        this.fc = fc;
    }

    public void setFic(int fic) {
        this.fic = fic;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public void setFoc(String foc) {
        this.foc = foc;
    }

    public void setSic(String sic) {
        this.sic = sic;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }
}
