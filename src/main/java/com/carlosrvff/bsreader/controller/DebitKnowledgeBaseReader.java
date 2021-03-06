package com.carlosrvff.bsreader.controller;

import com.carlosrvff.bsreader.domain.kb.DebitKnowledgeItem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

@Log4j2
public class DebitKnowledgeBaseReader {

  public List<DebitKnowledgeItem> load(@NonNull String fileName) throws IOException {
    List<DebitKnowledgeItem> result = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      processContent(result, bufferedReader);
    }
    return result;
  }

  private void processContent(List<DebitKnowledgeItem> result, BufferedReader bufferedReader)
      throws IOException {
    HashMap<String, ArrayList<String>> contentHash = convertContentToHash(bufferedReader);
    contentHash.forEach(
        (key, value) -> {
          result.add(DebitKnowledgeItem.builder().name(key).keywords(value).build());
        });
  }

  private HashMap<String, ArrayList<String>> convertContentToHash(BufferedReader bufferedReader)
      throws IOException {
    HashMap<String, ArrayList<String>> result = new HashMap<>();
    String currentKey = "";
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      currentKey = processLine(result, currentKey, line);
    }
    return result;
  }

  private String processLine(
      HashMap<String, ArrayList<String>> contentHash, String currentKey, String line) {
    if (StringUtils.isBlank(line)){
      return currentKey;
    }
    if (isNewDebitKnowledgeItem(line)) {
      currentKey = line;
      contentHash.put(line, new ArrayList<>());
    } else {
      if (contentHash.containsKey(currentKey)) {
        contentHash.get(currentKey).add(line);
      }
    }
    return currentKey;
  }

  private boolean isNewDebitKnowledgeItem(@NonNull String line) {
    return line.charAt(0) == '*';
  }
}
