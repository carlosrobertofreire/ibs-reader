package com.carlosrobertofreire.ibsreader;

public class KnowledgeItem {

    private String name;
    private String[] keywords;
    private Statement[] statements;

    public KnowledgeItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public Statement[] getStatements() {
        return statements;
    }

    public void setStatements(Statement[] statements) {
        this.statements = statements;
    }
}
