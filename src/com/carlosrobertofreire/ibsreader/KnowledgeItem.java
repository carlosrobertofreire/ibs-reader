package com.carlosrobertofreire.ibsreader;

import java.util.ArrayList;

public class KnowledgeItem {

    private String name;
    private String[] keywords;
    private ArrayList<Statement> statements;

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

    public void addStatement(Statement s) {
        if (statements == null) statements = new ArrayList<Statement>();
        statements.add(s);
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }


}
