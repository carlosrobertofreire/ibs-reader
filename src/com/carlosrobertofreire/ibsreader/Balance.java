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
    public String toString() {
        StringBuilder sb = getStatementStringBuilder();
        sb.append(" Value: ");
        sb.append(getValue());
        return sb.toString();
    }

}
