package com.example.decorate.domain.dto;

public class AtpOpen {
    String hetfo;
    String kedd;
    String szerda;
    String csutortok;
    String pentek;
    String szombat;
    String vasarnap;

    public String getHetfo() {
        return hetfo;
    }

    public void setHetfo(String hetfo) {
        this.hetfo = hetfo;
    }

    public String getKedd() {
        return kedd;
    }

    public void setKedd(String kedd) {
        this.kedd = kedd;
    }

    public String getSzerda() {
        return szerda;
    }

    public void setSzerda(String szerda) {
        this.szerda = szerda;
    }

    public String getCsutortok() {
        return csutortok;
    }

    public void setCsutortok(String csutortok) {
        this.csutortok = csutortok;
    }

    public String getPentek() {
        return pentek;
    }

    public void setPentek(String pentek) {
        this.pentek = pentek;
    }

    public String getSzombat() {
        return szombat;
    }

    public void setSzombat(String szombat) {
        this.szombat = szombat;
    }

    public String getVasarnap() {
        return vasarnap;
    }

    public void setVasarnap(String vasarnap) {
        this.vasarnap = vasarnap;
    }

    @Override
    public String toString() {
        return "AtpOpen{" +
                "hetfo='" + hetfo + '\'' +
                ", kedd='" + kedd + '\'' +
                ", szerda='" + szerda + '\'' +
                ", csutortok='" + csutortok + '\'' +
                ", pentek='" + pentek + '\'' +
                ", szombat='" + szombat + '\'' +
                ", vasarnap='" + vasarnap + '\'' +
                '}';
    }
}
