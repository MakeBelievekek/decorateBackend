package com.example.decorate.domain.dto;

public class Atp {

    int place_id;
    String operator_id;
    String name;
    String vapt;
    String olapt;
    String japt;
    String ssapt;
    String sdapt;
    String group;
    String address;
    int zip;
    String city;
    String street;
    String findme;
    double geolat;
    double geolng;
    AtpOpen open;


    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getJapt() {
        return japt;
    }

    public void setJapt(String japt) {
        this.japt = japt;
    }

    public String getSsapt() {
        return ssapt;
    }

    public void setSsapt(String ssapt) {
        this.ssapt = ssapt;
    }

    public String getSdapt() {
        return sdapt;
    }

    public void setSdapt(String sdapt) {
        this.sdapt = sdapt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVapt() {
        return vapt;
    }

    public void setVapt(String vapt) {
        this.vapt = vapt;
    }

    public String getOlapt() {
        return olapt;
    }

    public void setOlapt(String olapt) {
        this.olapt = olapt;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFindme() {
        return findme;
    }

    public void setFindme(String findme) {
        this.findme = findme;
    }

    public double getGeolat() {
        return geolat;
    }

    public void setGeolat(double geolat) {
        this.geolat = geolat;
    }

    public double getGeolng() {
        return geolng;
    }

    public void setGeolng(double geolng) {
        this.geolng = geolng;
    }

    public AtpOpen getOpen() {
        return open;
    }

    public void setOpen(AtpOpen open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "Atp{" +
                "place_id=" + place_id +
                ", operator_id='" + operator_id + '\'' +
                ", name='" + name + '\'' +
                ", vapt='" + vapt + '\'' +
                ", olapt='" + olapt + '\'' +
                ", japt='" + japt + '\'' +
                ", ssapt='" + ssapt + '\'' +
                ", sdapt='" + sdapt + '\'' +
                ", group='" + group + '\'' +
                ", address='" + address + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", findme='" + findme + '\'' +
                ", geolat=" + geolat +
                ", geolng=" + geolng +
                ", open=" + open +
                '}';
    }
}
