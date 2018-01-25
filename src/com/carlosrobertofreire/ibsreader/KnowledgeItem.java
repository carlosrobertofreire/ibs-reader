package com.carlosrobertofreire.ibsreader;

import java.util.ArrayList;

public class KnowledgeItem {

    private String name;
    private String[] keywords;
    private ArrayList<Statement> statements;


    public KnowledgeItem() {
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        for (Statement statement : statements){
            sb.append(statement.getOriginalText()
            );
            sb.append("\n");
        }
        sb.append("\n");
        for (int i = 0; i < statements.size(); i++){
            Statement statement = statements.get(i);
            sb.append(statement.getValue());
            if (i != statements.size() - 1){
                sb.append(" + ");
            }
        }
        return sb.toString();
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
