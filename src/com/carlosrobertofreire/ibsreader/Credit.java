package com.carlosrobertofreire.ibsreader;

public class Credit extends Statement {

    private String from;

    public Credit(){
        super();
    }

    public Credit(String date, String from, String value){
        super(date, value);
        this.from = from;
    }

    @Override
    public String getPrefix() {
        return "CREDIT";
    }

    @Override
    public String getContent(){
        StringBuilder sb = new StringBuilder(20);
        sb.append(" From: ");
        sb.append(getFrom());
        return sb.toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
