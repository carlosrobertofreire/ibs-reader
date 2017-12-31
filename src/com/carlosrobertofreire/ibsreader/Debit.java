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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString(){
        StringBuilder sb = getStatementStringBuilder();
        sb.append(" Store: ");
        sb.append(store);
        sb.append(" Value: ");
        sb.append(getValue());
        return sb.toString();
    }
}
