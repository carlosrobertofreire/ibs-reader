package com.carlosrobertofreire.myapp;

import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.Statement;

public class Main {

    public static void main(String[] args) {
        Statement[] statements = Extract.getStatements();
        System.out.println(statements.length + " statements found!");
        for (Statement statement : statements){
            System.out.println(statement);
        }
    }


}
