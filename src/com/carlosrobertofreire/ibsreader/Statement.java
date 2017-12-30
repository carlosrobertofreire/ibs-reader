package com.carlosrobertofreire.ibsreader;

public class Statement {

    private String date;
    private String store;
    private String type;
    private String value;

    public Statement(){}

    public Statement(String date, String store, String value, String type){
        this.date = date;
        this.store = store.trim();
        this.value = value;
        this.type = type;
    }

    public Statement(String date, String value, String type){
        this.date = date;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString(){
        if (this.type.equals("Balance"))
            return "Date: " + this.date + "\nValue: " + this.value + "\nType: " + this.type;
        else
            return "Date: " + this.date + "\nStore: " + this.store + "\nValue: " + this.value + "\nType: " + this.type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
