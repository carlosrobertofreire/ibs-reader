package com.carlosrobertofreire.ibsreader;

public class Balance extends Statement {

    public Balance(){
        super();
    }

    public Balance(String date, String value, String originalText){
        super(date, value, originalText);
    }

    @Override
    protected String getPrefix() {
        return "BALANCE";
    }

    @Override
    protected String getContent(){
        return "";
    }

}
