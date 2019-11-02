package com.github.carlosrvff.bsreader.converter.domain;

public class Credit extends Statement {

    private String from;

    public Credit(){
        super();
    }

    public Credit(String date, String from, String value, String originalText){
        super(date, value, originalText);
        this.from = from;
    }

    @Override
    protected String getPrefix() {
        return "CREDIT";
    }

    @Override
    protected String getContent(){
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
