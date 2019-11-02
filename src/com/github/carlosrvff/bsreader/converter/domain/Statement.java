package com.github.carlosrvff.bsreader.converter.domain;

public abstract class Statement {

    private String date;
    private String value;
    private String originalText;

    public Statement(){}

    public Statement(String date, String value, String originalText){
        this.date = date;
        this.value = value;
        this.originalText = originalText;
    }

    protected abstract String getPrefix();

    protected abstract String getContent();

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(80);
        sb.append(getPrefix());
        sb.append(" ");
        sb.append("Date: ");
        sb.append(getDate());
        sb.append(getContent());
        sb.append(" Value: ");
        sb.append(getValue());
        return sb.toString();
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

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }
}
