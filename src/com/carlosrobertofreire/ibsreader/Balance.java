package com.carlosrobertofreire.ibsreader;

public class Balance extends Statement {

    public Balance(){
        super();
    }

    public Balance(String date, String value){
        super(date,value);
    }


    @Override
    public String getPrefix() {
        return "BALANCE";
    }

    @Override
    public String getContent(){
        return "";
    }

}
