package com.carlosrobertofreire.ibsreader;

public class Debit extends Statement {

    private String store;

    public Debit(String date, String store, String value, String originalText){
        super(date,value,originalText);
        this.store = store;
    }

    public Debit(){
        super();
    }

    @Override
    protected String getPrefix() {
        return "DEBIT";
    }

    @Override
    protected String getContent(){
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
