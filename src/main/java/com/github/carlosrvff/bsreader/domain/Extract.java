package com.github.carlosrvff.bsreader.domain;

import com.github.carlosrvff.bsreader.converter.ItauConverter;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Extract {

  private static ArrayList<Statement> statements;

  static {
    loadStatements();
  }

  private static void loadStatements() {
    String userHome = System.getProperty("user.home");
    String fileName = userHome + "/IBSReader/input.txt";

    System.out.println("Loading statements from " + fileName);

    statements = new ArrayList<Statement>();
    ItauConverter itauConverter = new ItauConverter();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        try {
          Statement statement = itauConverter.toStatement(line);
          statements.add(statement);
        } catch (InvalidStatementException e) {
          System.out.println(e.getMessage());
          if (e.getCause() != null) {
            System.out.println("  Original exception: " + e.getCause().getMessage());
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static ArrayList<Statement> getStatements() {
    return statements;
  }
}
