package com.carlosrobertofreire.myapp;

import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.KnowledgeBase;
import com.carlosrobertofreire.ibsreader.Statement;

public class DebitStatementsReport {

    public static void main(String[] args) {
        Statement[] statements = Extract.getStatements();
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        for (Statement statement : statements){
            System.out.println(statement);
        }

        // LOAD EXTRACT STATEMENTS
        // LOAD KNOWLEDGE BASE
        // FOR EACH STATEMENT
            //CHECK IF IS DEBIT STATEMENT
                // FOR EACH KNOWLEDGE ITEM INSIDE KNOWLEDGE BASE
                    // FOR EACH KEYWORDS INSIDE KNOWLEDGE ITEM
                        // TEST IF KEYWORD CONTAINS IN DEBIT STATEMENT STORE
                            // ADD DEBIT STATEMENT INTO KNOWLEDGE ITEM STATEMENTS
                            // NEXT DEBIT STATEMENT
                        // OTHERWISE
                            //NEXT KEYWORD
            // OTHERWISE
                //END
        // FOR EACH KNOWLEDGE ITEM
            // PRINT KNOWLEDGE ITEM NAME
            // PRINT KNOWLEDGE ITEM STATEMENTS
            // PRINT STATEMENTS VALUES SUM EXPRESSION



    }


}
