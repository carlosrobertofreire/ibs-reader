package com.carlosrobertofreire.ibsreader;

public abstract class Statement {

    private String date;
    private String value;

    public Statement(){}

    public Statement(String date, String value){
        this.date = date;
        this.value = value;
    }

    public abstract String getPrefix();

    protected StringBuilder getStatementStringBuilder(){
        StringBuilder sb = new StringBuilder(80);
        sb.append(getPrefix());
        sb.append(" ");
        sb.append("Date: ");
        sb.append(getDate());
        return sb;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
