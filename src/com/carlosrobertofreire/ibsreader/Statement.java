package com.carlosrobertofreire.ibsreader;

public class Statement {

    private String date;
    private String store;
    private StatementType type;
    private String value;

    public Statement(){}

    public Statement(String date, String store, String value, StatementType type){
        this.date = date;
        this.store = store.trim();
        this.value = value;
        this.type = type;
    }

    public Statement(String date, String value, StatementType type){
        this.date = date;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(80);
        sb.append("Date: ");
        sb.append(date);
        if (!type.equals(StatementType.Balance)){
            sb.append(" Store: ");
            sb.append(store);
        }
        sb.append(" Value: ");
        sb.append(value);
        sb.append(" Type: ");
        sb.append(type);
        return sb.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public StatementType getType() {
        return type;
    }

    public void setType(StatementType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
