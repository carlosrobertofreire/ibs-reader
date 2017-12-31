package com.carlosrobertofreire.ibsreader;

public class Debit extends Statement {

    private String store;

    public Debit(String date, String store, String value){
        super(date,value);
        this.store = store;
    }

    public Debit(){
        super();
    }

    @Override
    public String getPrefix() {
        return "DEBIT";
    }

    @Override
    public String getContent(){
        StringBuilder sb = new StringBuilder(20);
        sb.append(" Store: ");
        sb.append(store);
        return sb.toString();
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }


}
